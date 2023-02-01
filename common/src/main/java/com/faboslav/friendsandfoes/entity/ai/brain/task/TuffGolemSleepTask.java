package com.faboslav.friendsandfoes.entity.ai.brain.task;

import com.faboslav.friendsandfoes.FriendsAndFoes;
import com.faboslav.friendsandfoes.entity.TuffGolemEntity;
import com.faboslav.friendsandfoes.entity.ai.brain.TuffGolemBrain;
import com.faboslav.friendsandfoes.init.FriendsAndFoesMemoryModuleTypes;
import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.server.world.ServerWorld;

public class TuffGolemSleepTask extends Task<TuffGolemEntity>
{
	private final static int GO_TO_SLEEP_POSITION_DURATION = 3600;

	public TuffGolemSleepTask() {
		super(ImmutableMap.of(
			FriendsAndFoesMemoryModuleTypes.TUFF_GOLEM_SLEEP_COOLDOWN.get(), MemoryModuleState.VALUE_ABSENT
		), GO_TO_SLEEP_POSITION_DURATION);
	}

	@Override
	protected boolean shouldRun(
		ServerWorld world,
		TuffGolemEntity tuffGolem
	) {
		if (
			tuffGolem.isGlued()
			|| tuffGolem.isInSleepingPose()
			|| tuffGolem.isAtHome() == false
		) {
			return false;
		}

		//FriendsAndFoes.getLogger().info("TuffGolemSleepTask true");
		return false;
	}

	@Override
	protected void run(
		ServerWorld world,
		TuffGolemEntity tuffGolem,
		long time
	) {
		tuffGolem.getNavigation().stop();
		tuffGolem.startSleeping();
	}

	@Override
	protected boolean shouldKeepRunning(
		ServerWorld world,
		TuffGolemEntity tuffGolem,
		long time
	) {
		return true;
	}

	@Override
	protected void keepRunning(
		ServerWorld serverWorld,
		TuffGolemEntity tuffGolem,
		long l
	) {
	}

	@Override
	protected void finishRunning(
		ServerWorld world,
		TuffGolemEntity tuffGolem,
		long time
	) {
		TuffGolemBrain.setSleepCooldown(tuffGolem);
		tuffGolem.startStanding();
	}
}
