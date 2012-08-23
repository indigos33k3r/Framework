package no.runsafe.framework.event.listener.entity;

import no.runsafe.framework.event.entity.IEntityShootBowEvent;
import no.runsafe.framework.event.listener.EventRouter;
import no.runsafe.framework.output.IOutput;
import no.runsafe.framework.server.event.entity.RunsafeEntityShootBowEvent;
import no.runsafe.framework.timer.IScheduler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityShootBowEvent;

public class EntityShootBow extends EventRouter<IEntityShootBowEvent, EntityShootBowEvent>
{
	public EntityShootBow(IOutput output, IScheduler scheduler, IEntityShootBowEvent handler)
	{
		super(output, scheduler, handler);
	}

	@EventHandler
	@Override
	public void AcceptEvent(EntityShootBowEvent event)
	{
		super.AcceptEvent(event);
	}

	public boolean OnEvent(EntityShootBowEvent event)
	{
		handler.OnEntityShootBowEvent(new RunsafeEntityShootBowEvent(event));
		return true;
	}
}
