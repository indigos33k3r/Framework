package no.runsafe.framework.event.listener.player;

import no.runsafe.framework.event.listener.EventRouter;
import no.runsafe.framework.server.event.player.RunsafePlayerCommandPreprocessEvent;
import no.runsafe.framework.event.player.IPlayerCommandPreprocessEvent;
import no.runsafe.framework.timer.IScheduler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreprocess extends EventRouter<IPlayerCommandPreprocessEvent, PlayerCommandPreprocessEvent>
{
	public PlayerCommandPreprocess(IScheduler scheduler, IPlayerCommandPreprocessEvent handler)
	{
		super(scheduler, handler);
	}

	// This one cannot be async, so don't check
	@Override
	@EventHandler
	public void AcceptEvent(PlayerCommandPreprocessEvent event)
	{
		OnEvent(event);
	}

	public void OnEvent(PlayerCommandPreprocessEvent event)
	{
		handler.OnBeforePlayerCommand(new RunsafePlayerCommandPreprocessEvent(event));
	}
}
