package no.runsafe.framework.minecraft.event.player;

import no.runsafe.framework.api.IKernel;
import no.runsafe.framework.RunsafePlugin;
import no.runsafe.framework.api.event.player.IPlayerChatEvent;
import no.runsafe.framework.internal.InjectionPlugin;
import no.runsafe.framework.internal.wrapper.ObjectWrapper;
import no.runsafe.framework.api.event.IFakeableEvent;
import no.runsafe.framework.minecraft.player.RunsafePlayer;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

//import org.bukkit.event.player.PlayerChatEvent;

public class RunsafePlayerChatEvent extends RunsafeCancellablePlayerEvent implements IFakeableEvent
{
	public RunsafePlayerChatEvent(AsyncPlayerChatEvent toWrap)
	{
		super(toWrap);
		event = toWrap;
	}

	public String getFormat()
	{
		return event.getFormat();
	}

	public void setFormat(String format)
	{
		event.setFormat(format);
	}

	public String getMessage()
	{
		return event.getMessage();
	}

	public void setMessage(String message)
	{
		event.setMessage(message);
	}

	@Override
	public RunsafePlayer getPlayer()
	{
		return ObjectWrapper.convert((OfflinePlayer) event.getPlayer());
	}

	public List<RunsafePlayer> getRecipients()
	{
		return ObjectWrapper.convert(event.getRecipients());
	}

	@Override
	public boolean Fire()
	{
		isFake = true;
		for (IKernel plugin : InjectionPlugin.Instances.values())
			for (IPlayerChatEvent listener : plugin.getComponents(IPlayerChatEvent.class))
				listener.OnPlayerChatEvent(this);
		return !isCancelled();
	}

	@Override
	public boolean isFake()
	{
		return isFake;
	}

	private final AsyncPlayerChatEvent event;
	private boolean isFake;
}
