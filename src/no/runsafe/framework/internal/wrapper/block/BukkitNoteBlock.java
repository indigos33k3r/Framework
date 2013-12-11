package no.runsafe.framework.internal.wrapper.block;

import no.runsafe.framework.minecraft.block.RunsafeBlockState;
import org.bukkit.block.NoteBlock;

public abstract class BukkitNoteBlock extends RunsafeBlockState
{
	protected BukkitNoteBlock(NoteBlock toWrap)
	{
		super(toWrap);
		noteBlock = toWrap;
	}

	public byte getRawNote()
	{
		return noteBlock.getRawNote();
	}

	public void setRawNote(byte b)
	{
		noteBlock.setRawNote(b);
	}

	public boolean play()
	{
		return noteBlock.play();
	}

	public boolean play(byte b, byte b1)
	{
		return noteBlock.play(b, b1);
	}

	protected final NoteBlock noteBlock;
}
