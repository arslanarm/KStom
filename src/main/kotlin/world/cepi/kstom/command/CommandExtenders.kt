package world.cepi.kstom.command

import kotlinx.coroutines.launch
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Arguments
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandExecutor
import net.minestom.server.command.builder.arguments.Argument
import net.minestom.server.command.builder.exception.ArgumentSyntaxException
import world.cepi.kstom.IOScope

public inline fun Command.addSyntax(crossinline lambda: suspend () -> Unit) {
    setDefaultExecutor { _, _ -> IOScope.launch { lambda() }}
}

public inline fun Command.addSyntax(crossinline lambda: suspend (sender: CommandSender) -> Unit) {
    setDefaultExecutor { sender, _ -> IOScope.launch { lambda(sender) }}
}

public inline fun Command.addSyntax(crossinline lambda: suspend (sender: CommandSender, args: Arguments) -> Unit) {
    defaultExecutor = CommandExecutor { sender, args -> IOScope.launch { lambda(sender, args) } }
}

public inline fun Command.addSyntax(
    vararg arguments: Argument<*>,
    crossinline lambda: suspend () -> Unit
) {
    addSyntax({ _, _ -> IOScope.launch { lambda() }}, *arguments)
}

public inline fun Command.addSyntax(
    vararg arguments: Argument<*>,
    crossinline lambda: suspend (sender: CommandSender) -> Unit
) {
    addSyntax({ sender, _ -> IOScope.launch { lambda(sender) }}, *arguments)
}

public inline fun Command.addSyntax(
    vararg arguments: Argument<*>,
    crossinline lambda: suspend (sender: CommandSender, args: Arguments) -> Unit
) {
    addSyntax({ sender, args -> IOScope.launch { lambda(sender, args)} }, *arguments)
}

public inline fun Command.addSyntax(
    vararg arguments: Argument<*>,
    crossinline condition: () -> Boolean,
    crossinline lambda: suspend () -> Unit
) {
    addSyntax(
        { _, _ -> condition() },
        { _, _ -> IOScope.launch { lambda() } },
        *arguments
    )
}

public inline fun Command.addSyntax(
    vararg arguments: Argument<*>,
    crossinline condition: (source: CommandSender) -> Boolean,
    crossinline lambda: suspend () -> Unit
) {
    addSyntax(
        { source, _ -> condition(source) },
        { _, _ -> IOScope.launch { lambda() } },
        *arguments
    )
}

public inline fun Command.addSyntax(
    vararg arguments: Argument<*>,
    crossinline condition: (source: CommandSender, commandString: String) -> Boolean,
    crossinline lambda: suspend () -> Unit
) {
    addSyntax(
        { source, string -> condition(source, string ?: "") },
        { _, _ -> IOScope.launch { lambda() } },
        *arguments
    )
}

public inline fun Command.addSyntax(
    vararg arguments: Argument<*>,
    crossinline condition: () -> Boolean,
    crossinline lambda: suspend (sender: CommandSender) -> Unit
) {
    addSyntax(
        { _, _ -> condition() },
        { sender, _ -> IOScope.launch { lambda(sender) } },
        *arguments
    )
}

public inline fun Command.addSyntax(
    vararg arguments: Argument<*>,
    crossinline condition: (source: CommandSender) -> Boolean,
    crossinline lambda: suspend (sender: CommandSender) -> Unit
) {
    addSyntax(
        { source, _ -> condition(source) },
        { sender, _ -> IOScope.launch { lambda(sender) } },
        *arguments
    )
}

public inline fun Command.addSyntax(
    vararg arguments: Argument<*>,
    crossinline condition: (source: CommandSender, commandString: String) -> Boolean,
    crossinline lambda: suspend (sender: CommandSender) -> Unit
) {
    addSyntax(
        { source, string -> condition(source, string ?: "") },
        { sender, _ -> IOScope.launch { lambda(sender) } },
        *arguments
    )
}

public inline fun Command.addSyntax(
    vararg arguments: Argument<*>,
    crossinline condition: () -> Boolean,
    crossinline lambda: suspend (sender: CommandSender, args: Arguments) -> Unit
) {
    addSyntax(
        { _, _ -> condition()},
        { sender, args -> IOScope.launch { lambda(sender, args) } },
        *arguments
    )
}

public inline fun Command.addSyntax(
    vararg arguments: Argument<*>,
    crossinline condition: (source: CommandSender) -> Boolean,
    crossinline lambda: suspend (sender: CommandSender, args: Arguments) -> Unit
) {
    addSyntax(
        { source, _ -> condition(source)},
        { sender, args -> IOScope.launch { lambda(sender, args) } },
        *arguments
    )
}

public inline fun Command.addSyntax(vararg arguments: Argument<*>, crossinline condition: (
    source: CommandSender,
    commandString: String
) -> Boolean, crossinline lambda: suspend (sender: CommandSender, args: Arguments) -> Unit) {
    addSyntax(
        { source, string -> condition(source, string ?: "")},
        { sender, args -> IOScope.launch { lambda(sender, args) } },
        *arguments
    )
}

public inline fun Command.setArgumentCallback(arg: Argument<*>, crossinline lambda: suspend () -> Unit) {
    setArgumentCallback({ _, _ -> IOScope.launch { lambda() } }, arg)
}

public inline fun Command.setArgumentCallback(arg: Argument<*>, crossinline lambda: suspend (source: CommandSender) -> Unit) {
    setArgumentCallback({ source, _ -> IOScope.launch { lambda(source) } }, arg)
}

public inline fun Command.setArgumentCallback(arg: Argument<*>, crossinline lambda: suspend (source: CommandSender, value: ArgumentSyntaxException) -> Unit) {
    setArgumentCallback({ source, value -> IOScope.launch { lambda(source, value) } }, arg)
}

public inline fun Command.default(crossinline block: suspend (sender: CommandSender, args: Arguments) -> Unit) {
    defaultExecutor = CommandExecutor { sender, args -> IOScope.launch { block(sender, args) } }
}