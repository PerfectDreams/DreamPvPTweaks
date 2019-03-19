package net.perfectdreams.dreampvptweaks

import net.perfectdreams.dreamcore.utils.KotlinPlugin
import net.perfectdreams.dreamcore.utils.registerEvents
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.block.data.BlockData
import org.bukkit.entity.*
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.material.MaterialData

class DreamPvPTweaks : KotlinPlugin(), Listener {
	override fun softEnable() {
		super.softEnable()
		registerEvents(this)
	}

	override fun softDisable() {
		super.softDisable()
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	fun onDamage(e: EntityDamageByEntityEvent) {
		val victim = e.entity
		val materialData = when (victim) {
			is Skeleton -> Material.BONE_BLOCK.createBlockData()
			is Ghast -> Material.BONE_BLOCK.createBlockData()
			is MagmaCube -> Material.LAVA.createBlockData()
			is Blaze -> Material.LAVA.createBlockData()
			is Creeper -> Material.GREEN_WOOL.createBlockData()
			is Slime -> Material.GREEN_WOOL.createBlockData()
			is Enderman -> Material.BLACK_WOOL.createBlockData()
			is EnderDragon -> Material.BLACK_WOOL.createBlockData()
			is WitherSkeleton -> Material.BLACK_WOOL.createBlockData()
			is Wither -> Material.BLACK_WOOL.createBlockData()
			else -> Material.NETHER_WART.createBlockData()
		}

		victim.world.spawnParticle(
				Particle.BLOCK_CRACK,
				victim.location.add(0.0, 0.5, 0.0),
				(e.finalDamage * 40).toInt(),
				0.0,
				0.0,
				0.0,
				materialData
		)
	}
}