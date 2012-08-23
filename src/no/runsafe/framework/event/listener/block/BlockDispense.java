package no.runsafe.framework.event.listener.block;

import no.runsafe.framework.event.block.IBlockDispense;
import no.runsafe.framework.event.listener.EventRouter;
import no.runsafe.framework.output.IOutput;
import no.runsafe.framework.server.ObjectWrapper;
import no.runsafe.framework.timer.IScheduler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockDispenseEvent;

public class BlockDispense extends EventRouter<IBlockDispense, BlockDispenseEvent>
{
	public BlockDispense(IOutput output, IScheduler scheduler, IBlockDispense handler)
	{
		super(output, scheduler, handler);
	}

	@EventHandler
	@Override
	public void AcceptEvent(BlockDispenseEvent event)
	{
		super.AcceptEvent(event);
	}

	public boolean OnEvent(BlockDispenseEvent event)
	{
		return handler.OnBlockDispense(
			ObjectWrapper.convert(event.getBlock()),
			ObjectWrapper.convert(event.getItem())
		);
	}
}

