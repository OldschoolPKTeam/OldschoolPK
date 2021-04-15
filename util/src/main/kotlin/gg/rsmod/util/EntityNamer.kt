package gg.rsmod.util

import java.util.HashSet

/**
 * An improved version of [net.runelite.cache.util.Namer].
 * Converts item/npc/etc names to property names.
 */
class EntityNamer {

    val used: MutableSet<String> = HashSet()

    fun name(rawName: String, id: Int): String? {
        var name = rawName
        name = sanitize(name) ?: return null
        if (used.contains(name)) {
            name = name + "_" + id
            assert(!used.contains(name))
        }
        used.add(name)
        return name
    }

    private fun sanitize(inputName: String): String? {
        var name = inputName
        val fractionRegex = "(\\d)\\/(\\d)(?:rds)?(?:ths)?".toRegex()
        val matches = fractionRegex.findAll(name as CharSequence).iterator()
        if (matches.hasNext()) {
            val match = matches.iterator().next()
            val numerator = match.groupValues[1]
            val denominator = match.groupValues[2]
            name = inputName.replace(fractionRegex, "${numerator}_${denominator}")
        }

        val s = removeTags(name)
                .toUpperCase()
                .replace("\\s?\\(".toRegex(), "_")
                .replace(' ', '_')
                .replace("+", "_PLUS")
                .replace("&", "AND")
                .replace("[^a-zA-Z0-9_]".toRegex(), "")
                .replace("__", "_")
        if (s.isBlank()) {
            return null
        }
        return when {
            s.isBlank() -> null
            Character.isDigit(s[0]) -> "_$s"
            else -> s
        }
    }

    private fun removeTags(str: String?): String {
        val builder = StringBuilder(str!!.length)
        var inTag = false
        for (element in str) {
            if (element == '<') {
                inTag = true
            } else if (element == '>') {
                inTag = false
            } else if (!inTag) {
                builder.append(element)
            }
        }
        return builder.toString()
    }

}