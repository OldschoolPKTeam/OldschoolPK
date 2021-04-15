package gg.rsmod.game.model.attr

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * A system responsible for storing and exposing [AttributeKey]s and their
 * associated values. The type of the key is inferred by the [AttributeKey]
 * used when putting or getting the value.
 *
 * @author Tom <rspsmods@gmail.com>
 */
class AttributeMap {

    private var attributes: MutableMap<AttributeKey<*>, Any> = HashMap(0)

    /**
     * Warning: You can't use this operator to retrieve [ObjectAttributeKey] or [MapAttributeKey]
     * you need to use the [get] function.
     */
    // TODO warning in dev mode while accessing this with Object/MapAttributeKey
    @Suppress("UNCHECKED_CAST")
    operator fun <T> get(key: AttributeKey<T>): T? = (attributes[key] as? T)

    /**
     * Returns a [HashMap] with the [key]s type parameters
     */
    inline fun <reified K, reified V> get(key: MapAttributeKey<K, V>): HashMap<K, V> {
        if(has(key)) if(this[key] is HashMap) return this[key]!!
        val type = object: TypeToken<HashMap<K, V>>() {}.type
        val map = gson.fromJson<HashMap<K, V>>(gson.toJson(this[key]), type)
        return put(key, map ?: HashMap<K, V>())[key]!!
    }

    /**
     * Returns a [Object] with the [key]s type parameters
     */
    inline fun <reified T> get(key: ObjectAttributeKey<T>): T {
        if(has(key)) if(this[key] is T) return this[key]!!
        val type = object: TypeToken<T>() {}.type
        val obj = gson.fromJson<T>(gson.toJson(this[key]), type)
        return put(key, obj ?: key.default.invoke())[key]!!
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getOrDefault(key: AttributeKey<T>, default: T): T = (attributes[key] as? T) ?: default

    /**
     * Gets the value but with type of any for special casting
     */
    fun <T> getTypeAny(key: AttributeKey<T>): Any? = (attributes[key])

    @Suppress("UNCHECKED_CAST")
    fun <T> put(key: AttributeKey<T>, value: T): AttributeMap {
        attributes[key] = value as Any
        return this
    }

    operator fun <T> set(key: AttributeKey<T>, value: T) {
        put(key, value)
    }

    fun remove(key: AttributeKey<*>) {
        attributes.remove(key)
    }

    fun has(key: AttributeKey<*>): Boolean = attributes.containsKey(key)

    fun clear() {
        attributes.clear()
    }

    fun removeIf(predicate: (AttributeKey<*>) -> Boolean) {
        val iterator = attributes.iterator()
        while (iterator.hasNext()) {
            val attr = iterator.next()
            if (predicate(attr.key)) {
                iterator.remove()
            }
        }
    }

    fun toPersistentMap(): Map<String, Any> = attributes.filterKeys { it.persistenceKey != null }.mapKeys { it.key.persistenceKey!! }

    companion object {
        val gson = Gson()
    }

}