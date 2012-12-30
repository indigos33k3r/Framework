package no.runsafe.framework.server.player;

import no.runsafe.framework.RunsafePlugin;
import no.runsafe.framework.hook.IPlayerDataProvider;
import no.runsafe.framework.hook.IPlayerLookupService;
import no.runsafe.framework.hook.IPlayerPermissions;
import no.runsafe.framework.hook.IPlayerVisibility;
import no.runsafe.framework.server.RunsafeLocation;
import no.runsafe.framework.server.RunsafeServer;
import no.runsafe.framework.server.RunsafeWorld;
import no.runsafe.framework.server.block.RunsafeBlock;
import no.runsafe.framework.server.entity.RunsafeLivingEntity;
import no.runsafe.framework.server.inventory.IInventoryHolder;
import no.runsafe.framework.server.inventory.RunsafeInventory;
import no.runsafe.framework.server.item.RunsafeItemStack;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RunsafePlayer extends RunsafeLivingEntity implements IInventoryHolder
{
	public RunsafePlayer(Player toWrap)
	{
		super(toWrap);
		player = toWrap;
		basePlayer = toWrap;
	}

	public RunsafePlayer(OfflinePlayer toWrap)
	{
		super((toWrap instanceof Player) ? (Player) toWrap : null);
		if (toWrap instanceof Player)
			player = (Player) toWrap;
		else
			player = null;
		basePlayer = toWrap;
	}

	public String getName()
	{
		return basePlayer.getName();
	}

	public boolean hasPlayedBefore()
	{
		return basePlayer.hasPlayedBefore();
	}

	public boolean isOnline()
	{
		return basePlayer.isOnline();
	}

	public void setPlayerListName(String playerName)
	{
		this.player.setPlayerListName(playerName);
	}

	public boolean isOP()
	{
		return basePlayer.isOp();
	}

	public boolean isSurvivalist()
	{
		return player.getGameMode().equals(GameMode.SURVIVAL);
	}

	public boolean isCreative()
	{
		return player.getGameMode().equals(GameMode.CREATIVE);
	}

	public boolean isAdventurer()
	{
		return player.getGameMode().equals(GameMode.ADVENTURE);
	}

	public boolean isWhitelisted()
	{
		return basePlayer.isWhitelisted();
	}

	public boolean isBanned()
	{
		return basePlayer.isBanned();
	}

	public void setBanned(boolean banned)
	{
		basePlayer.setBanned(banned);
	}

	public void kick(String reason)
	{
		player.kickPlayer(reason);
	}

	public float getXP()
	{
		if (player == null)
			return 0;

		return player.getExp();
	}

	public void setXP(float points)
	{
		if (player != null)
			player.setExp(points);
	}

	public int getLevel()
	{
		if (player == null)
			return 0;

		return player.getLevel();
	}

	public void setLevel(int level)
	{
		if (player != null)
			player.setLevel(level);
	}

	public void sendBlockChange(RunsafeBlock block, byte data)
	{
		sendBlockChange(block.getLocation(), block.getTypeId(), data);
	}

	public void sendBlockChange(RunsafeLocation location, int itemId, byte data)
	{
		if (player != null)
			player.sendBlockChange(location.getRaw(), itemId, data);
	}

	public void teleport(RunsafeWorld world, double x, double y, double z)
	{
		teleport(new RunsafeLocation(world, x, y, z));
	}

	public RunsafeItemStack getItemInHand()
	{
		if (player != null)
			return new RunsafeItemStack(player.getItemInHand());

		return null;
	}

	public void sendMessage(String message)
	{
		if (player != null)
			player.sendMessage(message);
	}

	public Player getRawPlayer()
	{
		return this.player;
	}

	public RunsafeInventory getInventory()
	{
		if (player != null)
			return new RunsafeInventory(player.getInventory());

		return null;
	}

	@SuppressWarnings("deprecation")
	public void updateInventory()
	{
		if (player != null)
			player.updateInventory();
	}

	public boolean hasPermission(String permission)
	{
		return player != null && player.hasPermission(permission);
	}

	public HashMap<String, String> getData()
	{
		HashMap<String, String> results = new HashMap<String, String>();
		for (IPlayerDataProvider provider : dataHooks)
			results.putAll(provider.GetPlayerData(this));
		return results;
	}

	public String getDataValue(String key)
	{
		HashMap<String, String> data = getData();
		if (data.containsKey(key))
			return data.get(key);
		return null;
	}

	public boolean canSee(RunsafePlayer target)
	{
		if (visibilityHooks.isEmpty())
			return true;
		for (IPlayerVisibility check : visibilityHooks)
			if (!check.canPlayerASeeB(this, target))
				return true;
		return false;
	}

	public List<String> getGroups()
	{
		ArrayList<String> result = new ArrayList<String>();
		for (IPlayerPermissions hook : permissionHooks)
		{
			List<String> groups = hook.getUserGroups(this);
			if (groups != null)
				result.addAll(groups);
		}
		return result;
	}

	public static ArrayList<IPlayerDataProvider> dataHooks = new ArrayList<IPlayerDataProvider>();
	public static ArrayList<IPlayerVisibility> visibilityHooks = new ArrayList<IPlayerVisibility>();
	public static ArrayList<IPlayerPermissions> permissionHooks = new ArrayList<IPlayerPermissions>();
	private final Player player;
	private final OfflinePlayer basePlayer;
}
