package net.barribob.boss.particle

import net.barribob.boss.Mod
import net.barribob.boss.mob.mobs.obsidilith.BurstAction
import net.barribob.boss.mob.mobs.obsidilith.WaveAction
import net.barribob.boss.utils.ModColors
import net.barribob.maelstrom.static_utilities.MathUtils
import net.barribob.maelstrom.static_utilities.RandomUtils
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.client.particle.SpriteProvider
import net.minecraft.particle.DefaultParticleType
import net.minecraft.util.registry.Registry
import kotlin.math.sin

object Particles {
    val DISAPPEARING_SWIRL: DefaultParticleType = Registry.register(
        Registry.PARTICLE_TYPE,
        Mod.identifier("disappearing_swirl"),
        FabricParticleTypes.simple()
    )

    val SOUL_FLAME: DefaultParticleType = Registry.register(
        Registry.PARTICLE_TYPE,
        Mod.identifier("soul_flame"),
        FabricParticleTypes.simple()
    )

    val LICH_MAGIC_CIRCLE: DefaultParticleType = Registry.register(
        Registry.PARTICLE_TYPE,
        Mod.identifier("magic_circle"),
        FabricParticleTypes.simple()
    )

    val OBSIDILITH_BURST: DefaultParticleType = Registry.register(
        Registry.PARTICLE_TYPE,
        Mod.identifier("obsidilith_burst"),
        FabricParticleTypes.simple()
    )

    val ENCHANT: DefaultParticleType = Registry.register(
        Registry.PARTICLE_TYPE,
        Mod.identifier("enchant"),
        FabricParticleTypes.simple()
    )

    val OBSIDILITH_BURST_INDICATOR: DefaultParticleType = Registry.register(
        Registry.PARTICLE_TYPE,
        Mod.identifier("obsidilith_burst_indicator"),
        FabricParticleTypes.simple()
    )

    val OBSIDILITH_WAVE: DefaultParticleType = Registry.register(
        Registry.PARTICLE_TYPE,
        Mod.identifier("obsidilith_wave"),
        FabricParticleTypes.simple()
    )

    val OBSIDILITH_WAVE_INDICATOR: DefaultParticleType = Registry.register(
        Registry.PARTICLE_TYPE,
        Mod.identifier("obsidilith_wave_indicator"),
        FabricParticleTypes.simple()
    )

    const val FULL_BRIGHT = 15728880

    fun clientInit() {
        val particleFactory = ParticleFactoryRegistry.getInstance()

        particleFactory.register(DISAPPEARING_SWIRL) { provider: SpriteProvider ->
            SimpleParticleFactory(provider) {
                SimpleParticle(it, {
                    RandomUtils.range(15, 20)
                })
            }
        }

        particleFactory.register(SOUL_FLAME) { provider: SpriteProvider ->
            SimpleParticleFactory(provider) {
                val particle = SimpleParticle(it, { RandomUtils.range(15, 20) })
                particle.setColorOverride { ModColors.COMET_BLUE }
                particle
            }
        }

        particleFactory.register(LICH_MAGIC_CIRCLE) { provider: SpriteProvider ->
            SimpleParticleFactory(provider) {
                val particle = SimpleParticle(it, { 40 })
                particle.setBrightnessOverride { FULL_BRIGHT }
                particle.scale(4f)
                particle
            }
        }

        particleFactory.register(OBSIDILITH_BURST) { provider: SpriteProvider ->
            SimpleParticleFactory(provider) {
                val particle = SimpleParticle(it, { RandomUtils.range(7, 15) })
                particle.setBrightnessOverride { FULL_BRIGHT }
                particle.scale(4f)
                particle.setColorOverride { age -> MathUtils.lerpVec(age, ModColors.ORANGE, ModColors.RUNIC_BROWN) }
                particle
            }
        }

        particleFactory.register(ENCHANT) { provider: SpriteProvider ->
            SimpleParticleFactory(provider) { context ->
                val particle = SimpleParticle(context, { RandomUtils.range(30, 50) }, false)
                particle.setBrightnessOverride { FULL_BRIGHT }
                particle.setScaleOverride { (sin(it.toDouble() * Math.PI) + 1f).toFloat() * 0.1f }
                particle
            }
        }

        particleFactory.register(OBSIDILITH_BURST_INDICATOR) { provider: SpriteProvider ->
            SimpleParticleFactory(provider) { context ->
                val particle = SimpleParticle(context, { BurstAction.burstDelay + RandomUtils.range(-1, 2) })
                particle.setColorOverride { ModColors.ORANGE }
                particle.setColorVariation(0.3)
                particle.setBrightnessOverride { FULL_BRIGHT }
                particle.setScaleOverride { (1 + it) * 0.25f }
                particle
            }
        }

        particleFactory.register(OBSIDILITH_WAVE) { provider: SpriteProvider ->
            SimpleParticleFactory(provider) {
                val particle = SimpleParticle(it, { RandomUtils.range(7, 15) })
                particle.setBrightnessOverride { FULL_BRIGHT }
                particle.scale(4f)
                particle.setColorOverride { age -> MathUtils.lerpVec(age, ModColors.RED, ModColors.DARK_RED) }
                particle
            }
        }

        particleFactory.register(OBSIDILITH_WAVE_INDICATOR) { provider: SpriteProvider ->
            SimpleParticleFactory(provider) { context ->
                val particle = SimpleParticle(context, { WaveAction.waveDelay + RandomUtils.range(-1, 2) })
                particle.setColorOverride { ModColors.RED }
                particle.setColorVariation(0.3)
                particle.setBrightnessOverride { FULL_BRIGHT }
                particle.setScaleOverride { (1 + it) * 0.25f }
                particle
            }
        }
    }
}