package no.runsafe.framework.server.item;

import no.runsafe.framework.server.enchantment.RunsafeEnchantmentWrapper;
import no.runsafe.framework.server.material.RunsafeMaterial;
import org.bukkit.inventory.ItemStack;

public class RunsafeItemStack
{
	public RunsafeItemStack(ItemStack stack)
	{
		itemStack = stack;
	}

    public RunsafeItemStack(int itemId)
    {
        itemStack = new ItemStack(itemId);
    }

    public RunsafeItemStack(RunsafeMaterial material)
    {
        itemStack = new ItemStack(material.getRaw());
    }

    public RunsafeItemStack(int materialId, int amount, short durability)
    {
        itemStack = new ItemStack(materialId, amount, durability);
    }

    public RunsafeItemStack(int materialId, int amount, short durability, Byte data)
    {
        itemStack = new ItemStack(materialId, amount, durability, data);
    }

    public boolean containsEnchantment(RunsafeEnchantmentWrapper enchantmentWrapper)
    {
        return itemStack.containsEnchantment(enchantmentWrapper.getRaw());
    }

    public int getItemId()
    {
        return itemStack.getTypeId();
    }

    public short getDurability()
    {
        return itemStack.getDurability();
    }

	public ItemStack getRaw()
	{
		return itemStack;
	}

	private ItemStack itemStack;
}