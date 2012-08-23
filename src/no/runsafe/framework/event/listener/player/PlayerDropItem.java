package no.runsafe.framework.event.listener.player;

import no.runsafe.framework.event.listener.EventRouter;
import no.runsafe.framework.event.player.IPlayerDropItemEvent;
import no.runsafe.framework.output.IOutput;
import no.runsafe.framework.server.event.player.RunsafePlayerDropItemEvent;
import no.runsafe.framework.timer.IScheduler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItem extends EventRouter<IPlayerDropItemEvent, PlayerDropItemEvent>
{
	public PlayerDropItem(IOutput output, IScheduler scheduler, IPlayerDropItemEvent handler)
	{
		super(output, scheduler, handler);
	}

	@EventHandler
	@Override
	public void AcceptEvent(PlayerDropItemEvent event)
	{
		super.AcceptEvent(event);
	}

	public boolean OnEvent(PlayerDropItemEvent event)
	{
		handler.OnPlayerDropItem(new RunsafePlayerDropItemEvent(event));
		return true;
	}
}
