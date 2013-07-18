package no.runsafe.framework.minecraft.event.inventory;

import no.runsafe.framework.internal.wrapper.ObjectWrapper;
import no.runsafe.framework.minecraft.player.RunsafePlayer;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class RunsafeInventoryCloseEvent extends RunsafeInventoryEvent
{
	public RunsafeInventoryCloseEvent(InventoryCloseEvent toWrap)
	{
		super(toWrap);
		event = toWrap;
	}

	public RunsafePlayer getPlayer()
	{
		return ObjectWrapper.convert(event.getPlayer());
	}

	private final InventoryCloseEvent event;
}
