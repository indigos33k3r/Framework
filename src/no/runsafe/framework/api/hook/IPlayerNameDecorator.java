package no.runsafe.framework.api.hook;

import no.runsafe.framework.minecraft.player.RunsafePlayer;

public interface IPlayerNameDecorator extends IFrameworkHook
{
	/**
	 * Called by {@link RunsafePlayer#getPrettyName()} to decorate a player name for output
	 */
	String DecorateName(RunsafePlayer player, String name);
}
