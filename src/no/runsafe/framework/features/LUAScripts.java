package no.runsafe.framework.features;

import no.runsafe.framework.api.event.IServerReady;
import no.runsafe.framework.api.log.IConsole;
import no.runsafe.framework.internal.lua.PluginRunner;
import org.picocontainer.Startable;

public class LUAScripts implements Startable, IServerReady
{
	public LUAScripts(PluginRunner runner, IConsole console)
	{
		this.runner = runner;
		this.console = console;
	}

	@Override
	public void start()
	{
		runner.loadAPI();
	}

	@Override
	public void stop()
	{
	}

	@Override
	public void OnServerReady()
	{
		runner.loadScripts();
	}

	private final PluginRunner runner;
	private final IConsole console;
}
