package net.perfectdreams.dreampvptweaks

import net.perfectdreams.dreamcore.utils.KotlinPlugin
import net.perfectdreams.dreamcore.utils.registerEvents
import org.bukkit.Material
import org.bukkit.Particle
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
			is Skeleton -> MaterialData(Material.BONE_BLOCK)
			is Ghast -> MaterialData(Material.BONE_BLOCK)
			is MagmaCube -> MaterialData(Material.LAVA)
			is Blaze -> MaterialData(Material.LAVA)
			is Creeper -> MaterialData(Material.GREEN_WOOL)
			is Slime -> MaterialData(Material.GREEN_WOOL)
			is Enderman -> MaterialData(Material.BLACK_WOOL)
			is EnderDragon -> MaterialData(Material.BLACK_WOOL)
			is WitherSkeleton -> MaterialData(Material.BLACK_WOOL)
			is Wither -> MaterialData(Material.BLACK_WOOL)
			else -> MaterialData(Material.NETHER_WART)
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