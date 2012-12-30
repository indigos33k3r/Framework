package no.runsafe.framework.event.listener.block;

import no.runsafe.framework.event.block.IBlockDispenseEvent;
import no.runsafe.framework.event.listener.EventRouter;
import no.runsafe.framework.output.IOutput;
import no.runsafe.framework.server.event.block.RunsafeBlockDispenseEvent;
import no.runsafe.framework.timer.IScheduler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockDispenseEvent;

@SuppressWarnings("deprecation")
@Deprecated
public class BlockDispenseListener extends EventRouter<IBlockDispenseEvent, BlockDispenseEvent>
{
	public BlockDispenseListener(IOutput output, IScheduler scheduler, IBlockDispenseEvent handler)
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
		handler.OnBlockDispenseEvent(new RunsafeBlockDispenseEvent(event));
		return true;
	}
}