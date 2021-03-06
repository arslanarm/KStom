package world.cepi.kstom.command

import net.minestom.server.command.CommandProcessor
import net.minestom.server.command.CommandSender
import net.minestom.server.entity.Player

/** Kotlin version of CommandProcessor using constructors instead of overriding methods */
public open class KommandProcessor(
        /** The name of the command. Used for bridager */
        public val name: String,

        /** Aliases, or alternative ways to type the command, stored in a list. */
        public val aliases: List<String> = emptyList(),

        /** The condition the player needs to meet to see the command. */
        public val condition: (player: Player) -> Boolean = { _ -> true },

        /** What runs when the command is run. */
        public val process: (sender: CommandSender, command: String, args: List<String>) -> Boolean = { _, _, _ -> true }
): CommandProcessor {
    override fun getCommandName(): String {
       return name
    }

    override fun getAliases(): Array<String> {
        return aliases.toTypedArray()
    }

    override fun process(sender: CommandSender, command: String, args: Array<out String>): Boolean {
        return process.invoke(sender, command, args.toList())
    }

    override fun hasAccess(player: Player): Boolean {
        return condition.invoke(player)
    }
}