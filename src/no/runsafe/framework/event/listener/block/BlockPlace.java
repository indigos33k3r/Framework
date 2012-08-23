package no.runsafe.framework.event.listener.block;

import no.runsafe.framework.event.block.IBlockPlace;
import no.runsafe.framework.event.listener.EventRouter;
import no.runsafe.framework.output.IOutput;
import no.runsafe.framework.server.ObjectWrapper;
import no.runsafe.framework.timer.IScheduler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace extends EventRouter<IBlockPlace, BlockPlaceEvent>
{
	public BlockPlace(IOutput output, IScheduler scheduler, IBlockPlace handler)
	{
		super(output, scheduler, handler);
	}

	@EventHandler
	@Override
	public void AcceptEvent(BlockPlaceEvent event)
	{
		super.AcceptEvent(event);
	}

	@EventHandler
	public boolean OnEvent(BlockPlaceEvent event)
	{
		return handler.OnBlockPlace(
			ObjectWrapper.convert(event.getPlayer()),
			ObjectWrapper.convert(event.getBlock())
		);
	}
}

