package gg.rsmod.game.configuration

/**
 * Property for the world configuration.
 */
class WorldConfigurationProperty(val key: String, val value: Any) {

    /**
     * Check if the [value] is a list.
     */
    fun isList(): Boolean {
        return asList() != null
    }

    /**
     * Convert the value to a list
     */
    @Suppress("UNCHECKED_CAST")
    private fun asList(): ArrayList<Any>? {
        return value as? ArrayList<Any>
    }

    /**
     * The value as a [List] of [LinkedHashMap].
     */
    @Suppress("UNCHECKED_CAST")
    fun listValues(includeExtend: Boolean = true): List<LinkedHashMap<String, Any>> {
        val list = asList()!!.map { it as LinkedHashMap<String, Any> }.toList()
        if (!includeExtend) {
            val mutable = list.toMutableList()
            mutable.removeIf { it.containsKey("extend") }
            return mutable.toList()
        }
        return list
    }

    /**
     * Is this property extending the already defined
     * list that's defined with this [key].
     */
    fun isExtending(): Boolean {
        if (isList()) {
            val listValues = listValues()
            val extendEntry = listValues.singleOrNull { it.containsKey("extend") }
            return if (extendEntry != null) {
                extendEntry["extend"] as Boolean
            } else false
        }

        return false
    }

}