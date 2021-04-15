package gg.rsmod.game.model.attr

/**
 * A [AttributeKey] used specifically for mapped objects
 */
class MapAttributeKey<K, V>(persistenceKey: String? = null, resetOnDeath: Boolean = false) :
    AttributeKey<HashMap<K, V>>(persistenceKey, resetOnDeath)