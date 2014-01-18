package no.runsafe.framework.api.command.argument;

import no.runsafe.framework.api.IServer;
import no.runsafe.framework.api.command.Command;
import no.runsafe.framework.api.command.ICommandExecutor;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.internal.InjectionPlugin;
import no.runsafe.framework.internal.Player;
import no.runsafe.framework.internal.command.BasePlayerArgument;
import no.runsafe.framework.internal.extension.player.RunsafeAmbiguousPlayer;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

@Deprecated
public class OnlinePlayerArgument extends BasePlayerArgument implements IValueProvider<IPlayer>
{
	public OnlinePlayerArgument()
	{
		this(false);
	}

	public OnlinePlayerArgument(boolean required)
	{
		this(required, false);
	}

	public OnlinePlayerArgument(boolean required, boolean context)
	{
		this("player", required, context);
	}

	public OnlinePlayerArgument(String name, boolean required)
	{
		this(name, required, false);
	}

	public OnlinePlayerArgument(String name, boolean required, boolean context)
	{
		super(name, required, context);
	}

	@Nullable
	@Override
	public String expand(ICommandExecutor context, @Nullable String value)
	{
		if (value == null)
			return super.expand(context, null);

		if (context instanceof IPlayer)
			return expandForPlayer((IPlayer) context, value);

		return expandForConsole(context, value);
	}

	@Nullable
	private String expandForConsole(ICommandExecutor context, @Nullable String value)
	{
		Matcher quoted = Command.QUOTED_ARGUMENT.matcher(value);
		if (quoted.matches())
		{
			IPlayer target = Player.Get().getExact(quoted.group(1));
			if (target.isOnline())
				return target.getName();
			return null;
		}

		List<String> matches = Player.Get().getOnline(value);
		if (matches.size() > 1)
		{
			context.sendColouredMessage(new RunsafeAmbiguousPlayer(null, matches).toString());
			if (!isRequired() && expand)
				return null;
		}
		if (matches != null && matches.size() == 1)
			return matches.get(0);

		return isRequired() ? Invalid : value;
	}

	@Nullable
	private String expandForPlayer(IPlayer context, String value)
	{
		Matcher quoted = Command.QUOTED_ARGUMENT.matcher(value);
		if (quoted.matches())
		{
			IPlayer target = Player.Get().getExact(quoted.group(1));
			if (context.shouldNotSee(target))
				return null;
			return quoted.group(1);
		}
		List<String> matches = Player.Get().getOnline(context, value);
		if (matches.size() > 1)
		{
			context.sendColouredMessage(new RunsafeAmbiguousPlayer(null, matches).toString());
			if (!isRequired() && expand)
				return null;
		}
		if (matches != null && matches.size() == 1)
			return matches.get(0);

		return isRequired() ? Invalid : value;
	}

	@Override
	public IPlayer getValue(IPlayer context, Map<String, String> params)
	{
		return InjectionPlugin.getGlobalComponent(IServer.class).getPlayerExact(params.get(name));
	}

	public static final String Invalid = "\0INVALID\0";
}
