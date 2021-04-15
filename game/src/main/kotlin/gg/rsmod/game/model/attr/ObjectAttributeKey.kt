package gg.rsmod.game.model.attr

/**
 * A [AttributeKey] specifically for serialized objects
 * @param default The default object of the Object Attribute Key
 */
class ObjectAttributeKey<T>(
    persistenceKey: String? = null,
    resetOnDeath: Boolean = false,
    val default: () -> T
) : AttributeKey<T>(persistenceKey, resetOnDeath)