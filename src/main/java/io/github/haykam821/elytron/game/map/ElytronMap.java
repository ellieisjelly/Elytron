package io.github.haykam821.elytron.game.map;

import java.util.Set;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.chunk.ChunkGenerator;
import xyz.nucleoid.map_templates.BlockBounds;
import xyz.nucleoid.map_templates.MapTemplate;
import xyz.nucleoid.plasmid.api.game.level.generator.TemplateChunkGenerator;

public class ElytronMap {
	private final MapTemplate template;
	private final AABB innerBox;
	private final AABB innerInnerBox;
	private final Vec3 waitingSpawnPos;

	public ElytronMap(MapTemplate template, BlockBounds bounds) {
		this.template = template;
		this.innerBox = bounds.asBox().inflate(-1, -1, -1);
		this.innerInnerBox = this.innerBox.inflate(-1, -1, -1);

		Vec3 center = this.innerBox.getCenter();
		this.waitingSpawnPos = new Vec3(center.x(), this.innerBox.minY, center.z());
	}

	public AABB getInnerBox() {
		return this.innerBox;
	}

	public AABB getInnerInnerBox() {
		return this.innerInnerBox;
	}

	public Vec3 getWaitingSpawnPos() {
		return this.waitingSpawnPos;
	}

	public void teleportToWaitingSpawn(ServerPlayer player) {
		Vec3 pos = this.getWaitingSpawnPos();
		player.teleportTo(player.level(), pos.x(), pos.y(), pos.z(), Set.of(), 0, 0, true);
	}

	public ChunkGenerator createGenerator(MinecraftServer server) {
		return new TemplateChunkGenerator(server, this.template);
	}
}