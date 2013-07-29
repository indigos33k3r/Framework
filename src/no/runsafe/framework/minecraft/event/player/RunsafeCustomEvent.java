package no.runsafe.framework.minecraft.event.player;

import no.runsafe.framework.api.IKernel;
import no.runsafe.framework.api.event.player.IPlayerCustomEvent;
import no.runsafe.framework.internal.InjectionPlugin;
import no.runsafe.framework.minecraft.RunsafeServer;
import no.runsafe.framework.minecraft.event.RunsafeInternalEvent;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

public abstract class RunsafeCustomEvent extends RunsafeInternalEvent
{
	protected RunsafeCustomEvent(RunsafePlayer player, String event)
	{
		this.player = player;
		this.event = event;
	}

	public RunsafePlayer getPlayer()
	{
		return player;
	}

	public String getEvent()
	{
		return event;
	}

	public abstract Object getData();

	@SuppressWarnings("MethodWithMultipleLoops")
	@Override
	public boolean Fire()
	{
		RunsafeServer.Instance.getDebugger().finer("Firing custom event %s.", getClass().getName());
		for (IKernel plugin : InjectionPlugin.Instances.values())
		{
			RunsafeServer.Instance.getDebugger().finer("Asking %s.", plugin.getClass().getName());
			for (IPlayerCustomEvent listener : plugin.getComponents(IPlayerCustomEvent.class))
			{
				RunsafeServer.Instance.getDebugger().finer("Telling %s.", listener.getClass().getName());
				listener.OnPlayerCustomEvent(this);
			}
		}
		return true;
	}

	private final RunsafePlayer player;
	private final String event;
}
