package io.github.haykam821.elytron.game.map;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ElytronMapConfig {
	public static final Codec<ElytronMapConfig> CODEC = RecordCodecBuilder.create(instance -> {
		return instance.group(
			Codec.INT.fieldOf("x").forGetter(ElytronMapConfig::getX),
			Codec.INT.fieldOf("y").forGetter(ElytronMapConfig::getY),
			Codec.INT.fieldOf("z").forGetter(ElytronMapConfig::getZ),
			BlockState.CODEC.optionalFieldOf("floor_provider", Blocks.SPRUCE_PLANKS.defaultBlockState()).forGetter(ElytronMapConfig::getFloorProvider),
			BlockState.CODEC.optionalFieldOf("wall_provider", Blocks.STRIPPED_DARK_OAK_LOG.defaultBlockState()).forGetter(ElytronMapConfig::getWallProvider),
			BlockState.CODEC.optionalFieldOf("ceiling_provider", Blocks.WHITE_STAINED_GLASS.defaultBlockState()).forGetter(ElytronMapConfig::getCeilingProvider)
		).apply(instance, ElytronMapConfig::new);
	});

	private final int x;
	private final int y;
	private final int z;
	private final BlockState floorProvider;
	private final BlockState wallProvider;
	private final BlockState ceilingProvider;

	public ElytronMapConfig(int x, int y, int z, BlockState floorProvider, BlockState wallProvider, BlockState ceilingProvider) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.floorProvider = floorProvider;
		this.wallProvider = wallProvider;
		this.ceilingProvider = ceilingProvider;
	}

	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

	public int getZ() {
		return this.z;
	}

	public BlockState getFloorProvider() {
		return this.floorProvider;
	}

	public BlockState getWallProvider() {
		return this.wallProvider;
	}

	public BlockState getCeilingProvider() {
		return this.ceilingProvider;
	}
}