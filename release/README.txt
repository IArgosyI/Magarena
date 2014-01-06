Magarena 
========
Homepage: http://magarena.googlecode.com
Forum   : http://www.slightlymagic.net/forum/viewforum.php?f=82

Requires: Java Runtime Environment 7 (http://java.com) or above must be installed on your computer

Starting Magarena:
  On Windows, double click on Magarena.exe
  On Linux, execute ./Magarena.sh. 
  On Mac, double click the Magarena icon

Magarena supports the following keyboard shortcuts:
  Space   or Right key : action button
  Escape  or Left key  : undo button
  F1      or M key     : show or hide game messages
  Y key                : yes button
  N key                : no button
  S key                : switch between player and AI view

Selecting the AI to play against:
  The desired AI can be selected in the "New duel" dialog (Arena -> New duel).
  The difficulty level is the maximum number of seconds the AI has to make a move.

Thanks to
  ubeefx for creating such a great game
  epiko for creating the Magarena logo and the amazing color themes
  Salasnet, pedro1973, and elias for creating beautiful themes, http://www.slightlymagic.net/forum/viewforum.php?f=89
  IcoJoy, http://www.icojoy.com/blogs/, for the nice free mage logo and icon
  singularita for creating the scripts to add over 300 additional creature cards
  glorfindel, Old Nick, David, Grundomu, jeffwadsworth, Kuno, LSK, sponeta,
  day5ive, and Excedrin for contributing premade decks
  mtgrares for the publicity
  Goblin Hero for providing the images for some of the symbols
  Melvin Zhang for implementing the Monte Carlo Tree Search AI and general code cleanup/bug fixes
  IanGrainger for contributing a patch to allow text search in the Card Explorer
  Rachel for making it possible to cancel image download and fixing incorrect images
  beholder for implementing new cards and general usability improvements
  johncpatterson for helping to test the Mac startup script
  wait321 for creating a deck editor and other UI improvements
  missalexis for creating an application bundle to improve Mac installation
  goonjamin for contributing a patch that makes Magarena easier to use on touchscreens
  PhazedOut, Aunukia, nado18, Erkcan Özcan, kdesmond, a. benedict balbuena,
  spartan vi, Braullynn, mecheng, pcastellazzi, rasdel, Tyrael, hong yie,
  ember hauler, Hector Marin, drooone, jericho.pumpkin, and Mike for implementing new cards
  everyone on the CCGHQ forum, http://slightlymagic.net/forum/

Thank you for your support and have fun!






Release 1.42 (September 29, 2013)
============
includes contributions from:
ember hauler
glorfindel
hong yie
Jericho Pumpkin
melvin
sponeta

- updated requirement from Java 6 to Java 7 as Java 6 has reached end of public updates

- show correct amount for charge counter, +1/+1, and -1/-1 counters when there are more than nine counters.

- allow gaining of ETB triggers via static ability

- added the following to the ability property in card script:
  * enters with X +1/+1
  * sac when targeted
  * enters effect <effect>
  * dies effect <effect>
  * leaves effect <effect>
  * poisonous <n>

- fixed the following bugs
  * Thundermaw Hellkite affects all opponent's creatures (instead of only those with flying)
  * Rootborn Defenses not giving creatures Indestructible
  * Woebearer's trigger return target creature card instead of target artifact card
  * Syncopate not using X from its mana cost in the counter event
  * Captain of the Mists tap/untap target creature (should be target permanent)
  * Pariah did not redirect damage
  * Lotus Guardian did not have flying
  * Sosuke's delayed trigger kept triggering if permanent is removed before trigger is attached
  * Obstinate Baloth put into graveyard when discarded (should be put on the battlefield)
  * number of cards in the graveyard not counted correctly when there are duplicates
  * Auras did not target when cast as a spell

- added/updated the following premade decks:
Evolution.dec, Haunted.dec, Landfall.dec, One_Creature_Challenge_40.dec,
Storm_Wind_and_Fire.dec, Unholy_Clerics.dec, Artifact_Recycler.dec, Birds.dec,
Burn_Myself.dec, Burning_Fury.dec, Card_Power.dec, Exalted_Lightning.dec,
Glint_60.dec, Glorfindel_GWR_Zoo.dec, Glorfindel_G_Garruk.dec,
Glorfindel_W_Auras-40.dec, Glorfindel_W_Elspeth.dec, Griffins.dec,
Holy_Wood.dec, Insect_Doom.dec, Pain_and_Gain.dec, Random_Usefulness.dec,
Sorcerer.dec, Spirit_Recycler.dec, Unlikely_Allies.dec, Vensers_Artifacts.dec,
White_Infect.dec, White_Infect_40.dec, Zombie_Overlord.dec,
Artifact_Recycler.dec, Birds.dec, Burn_Myself.dec, Burning_Fury.dec,
Card_Power.dec, Enchantress.dec, Exalted_Lightning.dec, Glint_60.dec,
Griffins.dec, Holy_Wood.dec, Insect_Doom.dec, Pain_and_Gain.dec,
Random_Usefulness.dec, Sorcerer.dec, Spirit_Recycler.dec, Unlikely_Allies.dec,
Vensers_Artifacts.dec, White_Infect.dec, White_Infect_40.dec,
Zombie_Overlord.dec, Glorfindel_GWR_Zoo.dec, Glorfindel_G_Garruk.dec,
Glorfindel_W_Auras-40.dec, Glorfindel_W_Elspeth.dec, 

- added the following cards:
Acidic Sliver, Ambassador Oak, Ancestor's Chosen, Anger of the Gods,
Annihilating Fire, Anowon, the Ruin Sage, Archdemon of Unx, Armor Sliver,
Arrogant Bloodlord, Attrition, Balthor the Stout, Barbed Sliver,
Bloodstained Mire, Brood Sliver, Cautery Sliver, Chromatic Lantern,
Clot Sliver, Crypt Sliver, Darkheart Sliver, Darksteel Brute,
Dismiss into Dream, Dormant Sliver, Dragon Broodmother, False Prophet,
Firewake Sliver, Flusterstorm, Forced Adaptation, Foundry Street Denizen,
Frenzy Sliver, Fungus Sliver, Ghostflame Sliver, Ghost Quarter,
Gift of Orzhova, Glimpse the Unthinkable, Goblin Matron, Gossamer Phantasm,
Harmonic Sliver, Hellraiser Goblin, Helm of Kaldra, Hibernation Sliver,
Hive Stirrings, Hivestone, Illusionary Servant, Ingot Chewer, Ivory Mask,
Lymph Sliver, Magma Sliver, Mephidross Vampire, Mindlash Sliver,
Mindwhip Sliver, Mirozel, Mistcutter Hydra, Mnemonic Sliver, Necrotic Sliver,
Nut Collector, Obzedat's Aid, Opaline Sliver, Opaline Unicorn,
Phantasmal Abomination, Phantasmal Bear, Phantasmal Dragon, Phantom Beast,
Plague Sliver, Poultice Sliver, Precinct Captain, Psionic Sliver,
Pulmonic Sliver, Quilled Sliver, Rageblood Shaman, Relic of Progenitus,
Screeching Sliver, Sedge Sliver, Serra Avatar, Shard Volley, Shield of Kaldra,
Sliver Legion, Solemn Simulacrum, Spectral Sliver, Spined Sliver,
Stony Silence, Sword of Kaldra, Sylvan Caryatid, Telekinetic Sliver,
Toxin Sliver, Victual Sliver, Virulent Sliver, Xathrid Necromancer, Zuran Orb

Release 1.41 (August 31, 2013)
============
includes contributions from:
drooone
elias
ember hauler
Hector Marin
hong yie
jericho.pumpkin
melvin
Mike

- given_ability property now supportes all abilities
  Note that both ability and given_ability property now separate abilities by
  ';' instead of ','

- added the following to the ability property in card script:
  * evoke <mana cost>
  * lord <affected permanents> have/has <abilities>
  * lord <affected permanents> get <power/toughness>
  * library instead of graveyard

- added support for the following in groovy script:
  * Clone mechanic
  * Cipher mechanic
  * gaining triggered and activated abilities
  * search from library and put onto the battlefield
  * search from library and put into hand

- improved busy spinning icon and summoning sickness icon

- fixed: only one instance of permanent can have its activated ability activated
- fixed: when life is lost trigger not triggering
- fixed: "application is damaged" warning on Mac OS
- fixed: Overload at instant speed
- fixed: Wilt-Leaf Liege enters the battlefield when milled

- added the following cards:
Accursed Spirit, Ajani's Chosen, Altar of Dementia, Anvil of Bogardan,
Ardent Plea, Argothian Enchantress, Arid Mesa, Assemble the Legion,
Battlegate Mimic, Battle Sliver, Blightsteel Colossus, Blood Reckoning,
Bloodshot Cyclops, Blur Sliver, Bonescythe Sliver, Borderland Ranger,
Captured Sunlight, Civic Wayfinder, Clone, Creeping Corrosion,
Darksteel Colossus, Dawnstrike Paladin, Deathgaze Cockatrice, Deceiver Exarch,
Deny Reality, Elvish Mystic, Enchantress's Presence, Enlisted Wurm,
Ethereal Armor, Etherium-Horn Sorcerer, Evil Twin, Fable of Wolf and Owl,
Farhaven Elf, Farseek, Fiendslayer Paladin, Flooded Strand, Fog Bank,
Fury Sliver, Galerider Sliver, Gemhide Sliver, Groundshaker Sliver,
Hands of Binding, Horizon Canopy, Kalonian Tusker, Kathari Remnant,
Manaweft Sliver, Marsh Flats, Master Transmuter, Megantic Sliver,
Millennial Gargoyle, Mind Sculpt, Minotaur Abomination, Misty Rainforest,
Mulldrifter, Necrogen Mists, Nether Void, Nightsky Mimic,
Obzedat, Ghost Council, Ondu Giant, Path to Exile, Perilous Forays,
Pestermite, Pestilence Demon, Phantasmal Image, Polluted Delta,
Predatory Sliver, Primeval Titan, Primordial Sage, Progenitor Mimic,
Progenitus, Rampant Growth, Regal Force, Regathan Firecat, Reminisce,
Riverfall Mimic, Rumbling Baloth, Rusted Slasher, Scalding Tarn,
Scourge of the Nobilis, Seacoast Drake, Sentinel Sliver, Shorecrasher Mimic,
Sliver Construct, Spell Snare, Spidersilk Armor, Spitting Sliver,
Starlit Sanctum, Steelform Sliver, Stolen Identity, Striking Sliver,
Sylvan Scrying, Syphon Sliver, Talon Sliver, Thorncaster Sliver,
Thorn Elemental, Tome Scour, Undead Minotaur, Verdant Catacombs,
Vinelasher Kudzu, Windswept Heath, Wirewood Savage, Wooded Foothills,
Woodlurker Mimic, Young Pyromancer

Release 1.40 (July 27, 2013)
============
includes contributions from:
a. benedict balbuena
abigail.davenport
elias
hmarinmacias
hong yie
manve.herumedre
melvin

- Groovy script able to add activated/triggered abilities to permanents using
  addActivation/addTrigger methods
- implemented the World rule
- added the following to the ability property in card script:
  * dead recover graveyard
  * opponent discard onto battlefield
  * can't be the target of nongreen spells or abilities from nongreen sources
  * enters may destroy <target>
  * protection from colored spells
  * protection from everything
  * ninjutsu <mana cost>
  * cascade

- fixed: incorrect URL for image of Arrest
- fixed: incorrect URL for image of Searing Spear
- fixed: incorrect URL for image of Sliver token
- fixed: incorrect URL for card text of Zhur-Taa Swine
- fixed: Hedge Troll's ability cost {G} instead of {W} (issue 359) 
- fixed: miracle trigger always treat X as 0 mana cost (issue 360)
- fixed: Soulbond still applied if target is invalid upon resolution (issue 361)
- fixed: Deadwood Treefolk can return itself from graveyard (issue 236)
- fixed: Bitterblossom could not be selected for Scion of Oona and Mistbind Clique's abilities
- fixed: Fumiko's bushido works only while defending
- fixed: Mad Auntie could not regenerate Goblin tribals
- fixed: Goblin King affect other goblins you control instead of all other goblins
- fixed: Vigor's ability was not working
- fixed: Mystic Restraints allows enchanted creature to untap
- fixed: Leap of Flame able to target any permanent
- fixed: Zealous Conscripts did not have haste
- fixed: AI not paying kicker cost
- fixed: bounce effect payed as part of a cost generally should not target

- added the following themes to https://code.google.com/p/magarena/downloads:
Crystal dragons bright, Crystal dragons default, Crystal dragons game mat,
Forest, Harmony, Island, Lotus, Mountain, Palace, Plains, Swamp, Temple,
Workshop

- added the following cards:
AEther Mutation, Agility, Ankh of Mishra, Apocalypse, Armored Ascension,
Bituminous Blast, Blessed Orator, Blind Creeper, Blinkmoth Nexus,
Blood Baron of Vizkopa, Bloodbraid Elf, Boros Guildgate, Brainstorm,
Briarknit Kami, Cabal Archon, Caltrops, Cartel Aristocrat, Cloudchaser Eagle,
Cranial Plating, Daunting Defender, Death Pits of Rath, Dimir Guildgate,
Doubtless One, Dryad Militant, Earthshaker, Ebony Owl Netsuke,
Eldrazi Conscription, Emrakul, the Aeons Torn, Eye of Nowhere, Fatal Frenzy,
Fireblast, Fulminator Mage, Gaea's Revenge, Glimmervoid, Grave Betrayal,
Grove of the Burnwillows, Gruul Guildgate, Guilty Conscience, Hermetic Study,
Hymn to Tourach, Ink-Eyes, Servant of Oni, Ivory Tower, Kami of Fire's Roar,
Kami of Tattered Shoji, Kami of the Crescent Moon, Kodama of the South Tree,
Lava Spike, Lightning Reflexes, Loxodon Smiter, Maelstrom Wanderer, Memnarch,
Mishra's Factory, Mugging, Mutilate, Ninja of the Deep Hours, Noxious Ghoul,
Obstinate Baloth, Okiba-Gang Shinobi, Olivia Voldaren, Orbweaver Kumo,
Orzhov Guildgate, Pariah, Phyrexian Negator, Pillar of Flame,
Pristine Talisman, Psionic Gift, Ray of Command, Reckless Abandon,
Renounce the Guilds, Rest in Peace, Scaled Hulk, Scavenging Ooze,
Shardless Agent, Sidewinder Sliver, Simic Guildgate, Sire of the Storm,
Skullcrack, Splinter Twin, Stream of Life, Strip Mine, Sublime Archangel,
Swans of Bryn Argoll, Syncopate, Tahngarth's Rage, Thundermaw Hellkite,
Tolarian Emissary, Underworld Connections, Varolz, the Scar-Striped,
Vexing Devil, Warleader's Helix, War Priest of Thune, Wilt-Leaf Liege

Release 1.39 (June 30, 2013)
============
includes contributions from melvin, a. benedict balbuena, manve.herumedre, and neilpturley

- Planeswalker and Legendary uniqueness rule apply per controller (Magic 2014 rules update)
- converted all cards to use dynamically loaded Groovy code instead of compiled Java code
- Groovy code for activated abilities no longer have to specify activation conditions
  that can be inferred from the activation cost
- added 'enters choose opponent' ability to card script
- implemented support for additional costs, see Magarena/scripts/Goblin_Grenade.groovy
- reimplement kicker, multikicker, buyback, and replicate as additional costs

- fixed: copied spell had the same player as original spell, causing copying
         an opponent's spell to not work properly (issue 353)
- fixed: Goblin Piledriver should get +2/+0 per other goblin, not +1/+0
- fixed: Wei Ambush Force should be 1/1, not 2/2
- fixed: Howling Mine should trigger in the draw phase instead of upkeep phase
- fixed: cards that enters tapped was triggering Gideon's Avenger
- fixed: Outwit should not be able to target spells that did not target a player 
- fixed: double clicking in choice menu could cause the game to hang

- added the following premade deck:
Mono-U_Frosty_Mill.dec

- added the following cards:
Abyssal Gatekeeper, Ambassador Laquatus, Anurid Barkripper, Armory Guard,
Aura Mutation, Backfire, Blitz Hellion, Boggart Sprite-Chaser, Bog Gnarr,
Bosh, Iron Golem, Breeding Pit, Celestial Ancient, Celestial Kirin,
Cloudhoof Kirin, Cloudreach Cavalry, Convolute, Court Homunculus,
Crimson Muckwader, Crowd of Cinders, Death Mutation, Drove of Elves,
Eyes of the Wisent, Faerie Swarm, Fatal Fumes, Flailing Drake, Flood,
Fungusaur, Ghostfire, Glade Gnarr, Goblin Grenade, Herd Gnarr,
Hindering Touch, Holy Mantle, Kithkin Greatheart, Kithkin Rabble,
Locust Swarm, Metamorphic Wurm, Mindstatic, Minotaur Tactician,
Miscalculation, Moriok Scavenger, Nimble Mongoose,
Okina, Temple to the Grandfathers, Oona, Queen of the Fae, Pestilence,
Plant Elemental, Psychic Strike, Punish Ignorance, Render Silent, Runeboggle,
Sanctum Gargoyle, Saproling Symbiosis, Sejiri Merfolk, Seton's Scout,
Skirk Outrider, Skizzik, Skyfire Kirin, Spell Snip, Springing Tiger,
Sunken City, Tek, Thoughtflare, Totally Lost, Valakut, the Molten Pinnacle,
Voice of Resurgence, Werebear, Woebearer, Woolly Mammoths, Worldspine Wurm

Release 1.38 (May 25, 2013)
============
includes contributions from melvin, sponeta, and a. benedict balbuena

- added 'given_type' property to card script
- added 'tap prevent damage <n>' ability to card script
- updated rules text parser with new keywords
- converted more cards from compiled card code to dynamic groovy code

- fixed: detain only lasts until end of turn, it should last until your next turn
- fixed: large memory usage when loading card text
- fixed: inconsistent AI hints for Soul Link, Spirit Link, and Vampiric Link
- fixed: Inkmoth Nexus tapping itself to pay for its second abililty
- fixed: prevent equipping an equipment on the same permanent
- fixed: crash due to infinite triggers during combat simulation
- fixed: Parallax Nexus not selecting target opponent
- fixed: 2/2 Knight token was missing vigilance
- fixed: 5/5 Wurm token was missing trample

- added the following premade decks:
Beastly_40.dec, Dogs_of_War_40.dec, Shaper.dec, Sliver_Rainbow.dec,
Soulmates.dec, Spirited_Away.dec, There_Will_Be_Blood.dec, Vampire_Blues.dec

- added the following cards:
Advent of the Wurm, Alabaster Wall, Ambition's Cost, Ancient Craving,
Arctic Aven, Armageddon, Armored Wolf-Rider, Arrest, Ascended Lawmage,
Azimaet Drake, Bane Alley Blackguard, Battering Krasis, Beast of Burden,
Benevolent Ancestor, Bloated Toad, Boros Mastiff, Brilliant Plan,
Call of the Conclave, Captain's Call, Careful Study, Centaur Glade,
Clergy en-Vec, Collective Blessing, Crag Puca, Darkwatch Elves,
Darting Merfolk, Disciple of Grace, Disciple of Law, Disciple of Malice,
Disciple of the Old Ways, Drake Hatchling, Drakewing Krasis, Drifting Meadow,
Ebony Rhino, Emblem of the Warmind, Endless Cockroaches, Expunge,
Femeref Healer, Fire Drake, Forgotten Cave, Frilled Oculus,
Ghor-Clan Bloodscale, Goblin Rally, Goblin Settler, Harbor Bandit,
Haunter of Nightveil, Healer of the Pride, Hedge Troll, Heedless One, Hush,
Intimidation, Kithkin Healer, Knight of the Skyward Eye, Knight Watch,
Kraul Warrior, Krovikan Mist, Lapse of Certainty, Lay Waste,
Lost Order of Jarkeld, Mana Tithe, Militant Monk, Mindwrack Liege, Mire Kavu,
Monk Realist, Mortus Strider, Multani, Maro-Sorcerer, Murmuring Phantasm,
Myr Quadropod, Noble Vestige, Ogre Arsonist, Phantasmal Fiend,
Phyrexian Gargantua, Plated Rootwalla, Polluted Mire, Prized Elephant,
Raise the Alarm, Rakdos Drake, Rakdos's Return, Ravages of War,
Ravaging Horde, Reckless One, Reinforced Bulwark, Rejuvenate, Riot Piker,
Rotlung Reanimator, Rubblebelt Maaka, Samite Archer, Sanctum Custodian,
Scornful AEther-Lich, Sedge Troll, Shivan Phoenix, Silverskin Armor,
Skylasher, Skyshroud Troopers, Sliptide Serpent, Sliver Queen, Soulless One,
Spark Trooper, Spike Jester, Spine of Ish Sah, Spitting Drake, Staff of Nin,
Steeple Roc, Tajic, Blade of the Legion, Talrand's Invocation, Tamanoa,
Tarpan, Tithe Drinker, Towering Thunderfist, Truefire Paladin,
Uktabi Orangutan, Unflinching Courage, Unyaro Bees, Viashino Slaughtermaster,
Viridian Corrupter, Vithian Renegades, Wake the Reflections, Wild Aesthir,
Woodlot Crawler

Release 1.37 (April 27, 2013)
============
includes contributions from melvin, sponeta, and hong yie Huang
- added non cheating monte carlo tree search AI
- added cheating vegas AI
- removed random AI
- updated AI Comparison page @  https://code.google.com/p/magarena/wiki/AIComparison

- improve speed of program startup by loading groovy/java code only when a card is
  needed in a match
- ignore corrupted card images and allow player to download new ones instead of crashing
- added Planeswalker to card type filter in card explorer
- draw mana icons for all permanents that generate mana, previously only limited to lands

- fixed: players always discard down to 7 cards regardless of their maximum hand size
- fixed: Planeswalker uniqueness rule not applied correctly as it was missing some Planwalker subtypes
- fixed: Esperzoa doesn't have flying
- fixed: evolve triggering when opponen's creature enters the battlefield
- fixed: desk strength calculation too slow

- added the following premade decks:
Graveyard_Eater.dec, Squirrels.dec, Swords_of_Enduring_Pain.dec,
UBmagarena.dec, UW_Spirit_Control.dec, Eldrazi_40.dec, Eldrazi_60.dec,
Elemental_Rage.dec, Fungus.dec, Human_Sacrifice.dec, Myr.dec, Weaken.dec,
Reanimation.dec

- added the following card:
Garruk's Packleader

Release 1.36 (March 29, 2013)
============
includes contributions from melvin, Jak, and sponeta 

- added support for Planeswalker card type. Note that creatures do not
  directly attack Planeswalkers, rather the damage redirect rule applies to
  both combat and non-combat damage.
- added keyboard shortcut 's' to show AI's hand
- limit thinking time of minimax AI to be AI strength in seconds
- converted more cards from compiled Java code to dynamically loaded groovy code

- fixed: Teleportal was using Vandalblast's image
- fixed: Taste of Blood gains life even if target is illegal
- fixed: Shattered Angel triggers when you play a land, it should only trigger when opponent plays a land.

- added the following premade decks:
Angels_and_Humans.dec, Essential_Black_40.dec, Fateful_Hour.dec,
Illusionists_Gambit_60.dec, JustRelentlessRats.dec, Life_Manipulator.dec,
Merfolk_Mayhem.dec, Rainbow.dec, Slivers.dec, Tapmaster.dec, Undying.dec,
Vanish_and_Reappear.dec, WB_Weenies.dec

- added the following cards:
Ajani, Caller of the Pride, Ajani Goldmane, Ajani Vengeant, Ant Queen,
Basandra, Battle Seraph, Break Asunder, Deathspore Thallid,
Elspeth, Knight-Errant, Elspeth Tirel, Experiment One, Flinthoof Boar,
Garruk, Primal Hunter, Gideon, Champion of Justice, Jace Beleren,
Krenko, Mob Boss, Psychotrope Thallid, Sarkhan Vol, Savage Thallid,
Searing Spear, Sharuum the Hegemon, Sphinx's Revelation,
Tamiyo, the Moon Sage, Thallid, Thallid Devourer, Thallid Germinator,
Thallid Shell-Dweller, Thistledown Liege, Tibalt, the Fiend-Blooded,
Venser, the Sojourner, Verdant Embrace, Vitaspore Thallid, Vraska the Unseen,
Wall of Blood, Yavimaya Scion, Zodiac Dragon

Release 1.35 (February 23, 2013)
============
includes contributions from melvin

- added the following new abilities to card script
  * cycling <mana cost>
  * reinforce <n> <mana cost>
  * evolve
  * extort
  * unleash
- added support for "creature you control" and "creature opponent control" to enchant property in card script
- converted 99 cards with card code to use groovy code
- added support for multiple card names to be specified in required_card_code and requires_groovy_code. 
  Card names should be separated by a single comma without any space
- removed white border around card pic pop up

- fixed: targetted storm spells copying one to many times
- fixed: Reya Dawnbringer's ability was triggering during opponent's upkeep instead of your upkeep
- fixed: able to tap Kessig Wolf Run to pay for the mana cost of its second ability
- fixed: Sphinx of Lost Truth only cause player to discard if it was NOT kicked.
- fixed: AI able to avoid targetting its own permanent for negative effects
- fixed: Tooth of Ramos was using Skull of Ramos card image

- added the following cards:
Adaptive Snapjaw, Angelsong, Azorius Arrester, Azusa, Lost but Seeking,
Basilica Guards, Basilica Screecher, Blind Obedience, Bloodfray Giant,
Blustersquall, Bomber Corps, Boros Elite, Boros Reckoner, Bounteous Kirin,
Brindle Shoat, Burrenton Bombardier, Carnival Hellsteed, Chaos Imps,
Chemister's Trick, Clinging Anemones, Cloudfin Raptor, Counterflux,
Coursers' Accord, Crocanura, Curse of Chains, Cyclonic Rift, Daring Skyjek,
Dead Reveler, Downsize, Druid's Deliverance, Dynacharge, Electrickery,
Elusive Krasis, Essence Warden, Exploration, Eyes in the Skies,
Firefist Striker, Firemane Avenger, Fleshbag Marauder, Force of Will,
Frontline Medic, Ghor-Clan Rampager, Gore-House Chainwalker, Grand Abolisher,
Grim Roustabout, Growing Ranks, Hellhole Flailer, Horncaller's Chant,
Inaction Injunction, Isperia's Skywatch, Kher Keep, Kingpin's Pet,
Knight of Obligation, Legion Loyalist, Lyev Skyknight, Martial Law,
Mitotic Slime, Mizzium Mortars, Mizzium Skin, Nav Squad Commandos,
Nefarox, Overlord of Grixis, New Prahv Guildmage, Ordruun Veteran,
Rakdos Cackler, Rootborn Defenses, Rubblehulk, Rukh Egg, Scab-Clan Charger,
Scorchwalker, Shambleshark, Skarrg Goliath, Skinbrand Goblin, Slaughterhorn,
Soulsworn Spirit, Spawn of Rix Maadi, Splatter Thug, Street Spasm,
Summer Bloom, Sundering Growth, Syndicate Enforcer, Syndic of Tithes,
Teleportal, Thrill-Kill Assassin, Trostani, Selesnya's Voice,
Trostani's Judgment, Urban Evolution, Vandalblast, Viashino Shanktail,
Vitu-Ghazi Guildmage, Warmind Infantry, Wasteland Viper, Wayfaring Temple,
Wojek Halberdiers, Wrecking Ogre, Zhur-Taa Swine

Release 1.34 (January 26, 2013)
============
includes contributions from melvin, Aunukia, and swanninger

- mana symbols shown on artifacts that generate mana
- improved automatic mana selection heuristic
- added the following new abilities to card script
  * enters destroy <target>.
  * sac at end
  * return at end
  * die return to owner's hand
  * control enchanted
  * tap add charge
  * untap <mana cost>
  * tap prevent damage 1
  * charge at upkeep
  * return to owner's hand <mana cost>
  * switch pt <mana cost>
- added "overwrite_color" property to card script
- added "requires_groovy_code" property to card script, card specific code
  can be written in Groovy and put in the scripts folder to be loaded when the
  game starts

- fixed: Dryad Arbor script missing color indicator
- fixed: Ghoulflesh did not make enchanted creature black
- fixed: Golgari Guildgate missing subtype Gate
- fixed: wrong URL for Regrowth's card text
- fixed: Sabertooth Nishoba missing subtype Cat and Warrior
- fixed: spell on stack can target itself, causes game to crash
- fixed: Cloudheath Drake missing flying ability

- added the following cards:
Abandoned Outpost, Abrupt Decay, Adarkar Wastes, Arctic Flats,
Battlefield Forge, Battle Squadron, Bee Sting, Befoul, Bog Wreckage,
Bond Beetle, Boreal Shelf, Brushland, Cackling Fiend, Cadaver Imp,
Caldera Lake, Carrion Feeder, Caves of Koilos, Colossus of Sardia,
Crimson Kobolds, Crookshank Kobolds, Crusader of Odric, Dakkon Blackblade,
Deadly Insect, Dreadwing, Druid of the Anima, Entangling Vines, Esperzoa,
Explosive Impact, Fault Line, Flesh-Eater Imp, Frost Marsh, Fyndhorn Elves,
Goblin Gardener, Hallowed Burial, Highland Weald, Ironshell Beetle,
Ivy Elemental, Karplusan Forest, Kiku's Shadow, Kobolds of Kher Keep,
Krakilin, Last Kiss, Lavalanche, Leaf Gilder, Llanowar Wastes, Manakin, Maro,
Mirrodin's Core, Morphling, Orochi Sustainer, Phyrexian Ghoul, Pine Barrens,
Psionic Blast, Ravaged Highlands, Revenant, Roaring Primadox, Salt Flats,
Scabland, Scarland Thrinax, Scarwood Treefolk, Scythe Tiger, Seafloor Debris,
Shifting Wall, Shivan Reef, Sinister Strength, Sisters of the Flame,
Skyshroud Forest, Smoldering Spires, Snow-Covered Forest, Snow-Covered Island,
Snow-Covered Mountain, Snow-Covered Plains, Snow-Covered Swamp,
Spirit of the Night, Sulfurous Springs, Supreme Verdict, Tendo Ice Bridge,
Timberland Ruins, Tranquility, Transguild Promenade, Turtleshell Changeling,
Underground River, Utopia Tree, Vampiric Link, Verduran Enchantress,
Wall of Opposition, Windreaver, Wirewood Elf, Yavimaya Coast, Zodiac Dog,
Zodiac Goat, Zodiac Horse, Zodiac Rabbit

Release 1.33 (December 30, 2012)
============
includes contributions from melvin, Aunukia, and PhazedOut

- rules parser is now able to generate a syntax tree from parsed rules text
- rules parser integrated into main program at src/magic/grammar

- fixed: changes to a creature's power and toughness may not be reflected immediately
- fixed: change of control of a creature in combat was not removing creature from combat 
- fixed: game crashes when sorting by power or toughness in deck editor
- fixed: message shown when activating a pump ability shows "+1/+-1" instead of "+1/-1"
- fixed: Sokenzan Spellblade's timing hint is set of flash, should be main
- fixed: Glimmerdust Nap was able to enchant untapped creatures
- fixed: Angelic Armaments doesn't make equipped creature white
- fixed: Curiosity able to draw card when dealing damage to any player, 
         should be only for opponent of controller of Curiosity

- added the following cards:
Alaborn Trooper, Ambush Party, Aspect of Mongoose, Auriok Champion,
Balduvian Bears, Barbarian Horde, Barbary Apes, Barktooth Warbeard, Bear Cub,
Blanchwood Treefolk, Bonded Fetch, Border Guard, Breath of Life,
Breathstealer, Brilliant Halo, Brimstone Dragon, Brute Force, Burrowing,
Capashen Knight, Capashen Templar, Carnivorous Plant, Caustic Rain,
Cave Sense, Coiled Tinviper, Concentrate, Copper Carapace, Crypt Ripper,
Dakmor Scorpion, Dark Banishing, Darksteel Forge, Defensive Stance,
Despondency, Dimir Cutpurse, Divinity of Pride, Dragon Engine, Dreadbore,
Dross Ripper, Dryad Arbor, Dryad's Favor, Durkwood Boars, Dwarven Trader,
Ebony Treefolk, Ekundu Griffin, Elves of Deep Shadow, Elvish Ranger,
Empty the Warrens, Erase, False Defeat, Femeref Scouts, Fetid Horror,
Fishliver Oil, Fissure, Flamekin Brawler, Fledgling Djinn, Forest Bear,
Foxfire Oak, Fractured Powerstone, Fresh Volunteers, Frozen Shade,
Furnace Spirit, Fusion Elemental, Gaea's Anthem, Genesis Chamber,
Gerrard's Irregulars, Glitterfang, Goblin Berserker, Goblin Bully,
Goblin Cavaliers, Goblin Elite Infantry, Goblin Hero, Golden Bear,
Gorilla Warrior, Gray Ogre, Groundbreaker, Gruesome Deformity, Hematite Golem,
Hero's Resolve, Hexplate Golem, Hoar Shade, Hornet Cobra, Hurloon Minotaur,
Iona's Judgment, Ironroot Treefolk, Jedit Ojanen, Jerrard of the Closed Fist,
Jeska, Warrior Adept, Jungle Lion, Juzam Djinn, Kasimir the Lone Wolf,
Keen Sense, Keepers of the Faith, Kjeldoran Outrider, Kranioceros,
Krenko's Command, Krovikan Scoundrel, Land Leeches, Launch, Leshrac's Rite,
Lizard Warrior, Llanowar Dead, Loch Korrigan, Loxodon Convert,
Malachite Golem, Mark of Fury, Meng Huo's Horde, Metallic Sliver,
Minotaur Warrior, Molten Ravager, Mons's Goblin Raiders, Mountain Bandit,
Muck Rats, Muzzle, Myr Matrix, Mystic Restraints, Narwhal, Nettletooth Djinn,
Neurok Commando, Oakenform, Ogre Berserker, Ogre Menial, Ogre Resister,
Ogre Warrior, Ophidian Eye, Panther Warriors, Parapet Watchers,
Pardic Collaborator, Pavel Maliki, Pearled Unicorn, Pillage, Plated Wurm,
Primeval Shambler, Puppeteer, Putrefax, Pygmy Razorback, Pyre Charger, Python,
Quilled Slagwurm, Raging Bull, Raging Cougar, Raging Minotaur,
Razorfield Thresher, Regal Unicorn, Restless Apparition, Resurrection,
Revoke Existence, Rotted Hystrix, Rowan Treefolk, Rummaging Goblin,
Saberclaw Golem, Sangrite Backlash, Scarwood Goblins, Sea Spirit,
Serra Zealot, Serum Tank, Shatter, Shrieking Mogg, Shu Elite Infantry,
Shu Foot Soldiers, Shuko, Simic Ragworm, Sir Shandlar of Eberyn,
Sivitri Scarzam, Skeletal Crocodile, Skeletal Snake, Sleeper's Guile,
Sluggishness, Smelt, Soliton, Squire, Steel Overseer, Stone Kavu,
Stone-Throwing Devils, Storm Shaman, Straw Soldiers, Talas Merchant,
Talisman of Dominance, Talisman of Impulse, Talisman of Indulgence,
Talisman of Progress, Talisman of Unity, Talruum Minotaur,
The Lady of the Mountain, Thorn-Thrash Viashino, Thought Courier, Thundermare,
Titanium Golem, Tobias Andrion, Tor Giant, Torment, Tormentor's Trident,
Torsten Von Ursus, Trained Jackal, Trokin High Guard, Undercity Shade,
Undying Rage, Verdigris, Viashino Cutthroat, Viashino Grappler,
Viashino Sandscout, Viashino Sandstalker, Viashino Slasher, Viashino Warrior,
Vodalian Soldiers, Volunteer Militia, Wall of Lava, Wall of Razors,
War Mammoth, Water Elemental, Water Servant, Wei Infantry, Whiptail Wurm,
Wild Colos, Wild Elephant, Wild Jhovall, Willow Elf, Wu Infantry, Zof Shade

Release 1.32 (November 30, 2012)
============
includes contributions from melvin, Erkcan Özcan, and nado18

- added official rules text parser based on Parsing Expression Grammar,
  not integrated into game engine yet

- fixed: Penumbra Spider missing reach
- fixed: Bonehoard crashes when played
- fixed: Ruptire Spire able to pay for itself
- fixed: Orim's Thunder crashes if initial target is invalid
- fixed: Restoration Angel able to return tokens
- fixed: Blood Artist triggers too few times after wrath effect
- fixed: unable to counter spells cast during declare attackers phase when no
         attackers were declared

- added the following cards:
Dispel
Stealer of Secrets
Symbol of Unsummoning

Release 1.31 (October 31, 2012)
============
includes contributions from melvin, a. benedict balbuena, kdesmond, and Erkcan Özcan

- added ability "tap pain add mana" to card script
- added ability "combat damage discard random card" to card script
- requires_card_code property can now specify a card name to reuse the card
  code for another card, e.g. requires_card_code=Cruel Edict

- fixed: Essence Drain only gains life if target is valid on resolution

- added the following cards:
Archweaver, Armada Wurm, Attended Knight, Aven Brigadier, Axebane Stag,
Azorius Guildgate, Barbtooth Wurm, Battering Sliver, Beetleback Chief,
Bellows Lizard, Blade Sliver, Blistering Barrier, Bloodstone Cameo,
Bonesplitter Sliver, Brushstrider, Catacomb Slug, Centaur Healer,
Charcoal Diamond, Coastal Tower, Cobblebrute, Concordia Pegasus,
Crash of Rhinos, Crystalline Sliver, Daggerdrome Imp, Darksteel Relic,
Devoted Hero, Diabolic Edict, Elfhame Palace, Essence Sliver, Fencing Ace,
Feral Shadow, Fire Diamond, Flameborn Viron, Frostburn Weird,
Golgari Guildgate, Golgari Longlegs, Grand Coliseum, Greater Forgeling,
Headless Horseman, Heart Sliver, Horned Sliver, Hornet Queen, Hover Barrier,
Hulking Goblin, Hulking Ogre, Hussar Patrol, Independent Troops,
Izzet Guildgate, Jasmine Boreal, Knightly Valor, Lady Orca, Lightning Hounds,
Lord of Atlantis, Marble Diamond, Mass Hysteria, Master of the Pearl Trident,
Meng Huo, Barbarian King, Metathran Soldier, Might Sliver, Minotaur Aggressor,
Moss Diamond, Muscle Sliver, Myr Sire, Obelisk of Bant, Obelisk of Esper,
Obelisk of Grixis, Obelisk of Jund, Obelisk of Naya, Obsianus Golem,
Penumbra Bobcat, Penumbra Kavu, Penumbra Spider, Penumbra Wurm,
Perilous Shadow, Plated Sliver, Pygmy Pyrosaur, Rakdos Guildgate,
Rakdos Ragemutt, Rakdos Ringleader, Rakdos Shred-Freak, Razorfin Hunter,
Reflex Sliver, Riot Spikes, Risen Sanctuary, Rootwater Hunter,
Rubbleback Rhino, Runewing, Salt Marsh, Sandstone Warrior, Seashell Cameo,
Selesnya Guildgate, Selesnya Sentry, Seller of Songbirds, Shadow Sliver,
Shambling Strider, Shivan Oasis, Sinew Sliver, Sky Diamond, Skyline Predator,
Southern Elephant, Spinneret Sliver, Stonefare Crocodile, Sunspire Griffin,
Symbiotic Beast, Symbiotic Elf, Symbiotic Wurm, Synapse Sliver,
Synchronous Sliver, Tenement Crasher, Thragtusk, Thunder-Thrash Elder,
Tigereye Cameo, Towering Indrik, Trained Caracal, Tresserhorn Sinks,
Trestle Troll, Troll-Horn Cameo, Tukatongue Thallid, Twisted Experiment,
Urborg Elf, Urborg Volcano, Vassal Soul, Vector Asp, Viashino Fangtail,
Wall of Heat, Watcher Sliver, Winged Sliver, Wizened Cenn, Yavimaya Ancients,
Zuberi, Golden Feather, Zuran Spellcaster

Release 1.30 (September 30, 2012)
============
includes contributions from melvin, a. benedict balbuena, and kdesmond

- mulligan option is always available
- added "effect" property to card script to allow spells to be scripted, 
  currently supports destroy, exile and counter
- added "kicker", "multikicker", and "enters with x" to "ability" property in card script,
  specific effect needs to be implemented as triggers in card code

- fixed: wrong URL of card image for Air Bladder, Horn of Ramos, and Mox Jet
- fixed: Twilight Drover's ability did not trigger for some types of tokens
- fixed: Berserker Murlodont was only affecting Beasts you control
- fixed: Edge of Divinity was missing "enchant" property in card script
- fixed: Arcbound Overseer's trigger not working on creatures with Modular

- added the following cards:
Alaborn Musketeer, Armored Pegasus, Arrogant Vampire, Battlefield Percher,
Belbe's Percher, Bird Maiden, Bloodied Ghost, City of Brass, Cloud Djinn,
Cloud Dragon, Cloud Pirates, Cloud Spirit, Cobalt Golem, Craterize,
Crazed Skirge, Dakmor Bat, Darklit Gargoyle, Death Stroke, Desert Drake,
Desert Twister, Djinn of the Lamp, Dread Reaper, Dungeon Shade,
Emerald Dragonfly, False Summoning, Firefly, Flying Men, Ghostly Visit,
Griselbrand, Gust-Skimmer, Hand of Death, Ice Storm, Killer Bees,
Killer Whale, Last Word, Lava Flow, Lotus Guardian, Mawcor, Mesa Falcon,
Misshapen Fiend, Moaning Spirit, Moon Sprite, Moonwing Moth, Murder,
Nightwing Shade, Patagia Golem, Pearl Dragon, Phantom Monster,
Preemptive Strike, Quiet Purity, Rishadan Airship, Roofstalker Wight,
Royal Falcon, Rune-Cervin Rider, Sabertooth Wyvern, Shifting Sliver,
Shriek Raptor, Silver Erne, Sinkhole, Sky Spirit, Spotted Griffin,
Starlit Angel, Stone Spirit, Stream Hopper, Sunder from Within,
Talas Air Ship, Talas Scout, Talonrend, Thornling, Thornwind Faeries,
Thunder Spirit, Thunder Wall, Torch Drake, Tormented Angel, Tower Drake,
Treetop Rangers, Vampiric Spirit, Vengeance, Venser's Sliver,
Viashivan Dragon, Volcanic Awakening, Volcano Imp, Ward Sliver,
Whiptongue Frog, Willow Faerie, Wing Snare, Winter's Grasp, Wreak Havoc

Release 1.29 (August 25, 2012)
============
includes contributions from melvin, a. benedict balbuena, spartan vi, Braullynn

- allow "replicate" keyword in ability property of card script 
- "value" property in card script should be determined from Gatherer's
  community rating
- improved checking of abilities in card script
- improved error message when card script cannot be loaded

- fixed: description of Smite the Monstrous's effect
- fixed: Kavu Predator missing trample
- fixed: Benalish Lancer only gained first strike until end of turn

- added the following premade decks:
2012Affinity.dec, 2012BWcontrol.dec, 2012RGfires.dec, 2012bant.dec,
2012bantGeist.dec, 2012bantTempo.dec, 2012bitterFaeries.dec,
2012blueControl.dec, 2012brAggro.dec, 2012brExalted.dec, 2012buZombies.dec,
2012bwExalted.dec, 2012dredge.dec, 2012elves.dec, 2012enchantress.dec,
2012gbControl.dec, 2012goblins.dec, 2012gwAggro.dec, 2012innistradDredge.dec,
2012innistradHumans.dec, 2012jund.dec, 2012kikiRestoration.dec,
2012landdestruction.dec, 2012lifegainWeenie.dec, 2012miracle.dec,
2012poison.dec, 2012poisonBudget.dec, 2012poisonBudgetPump.dec,
2012poisonGW.dec, 2012reanimator.dec, 2012redDeckWins.dec, 2012rgwAggro.dec,
2012rgwZoo.dec, 2012spirits.dec, 2012stompy.dec, 2012suicideBlack.dec,
2012ubMIll.dec, 2012uwControl.dec, 2012uwControlagain.dec, 2012wbtokens.dec,
2012zombies.dec

- added the following cards:
Abbey Gargoyles, Advanced Hoverguard, Aesthir Glider, Air Bladder,
Ajani's Sunstriker, Alaborn Grenadier, Alert Shu Infantry,
Angelic Benediction, Angel of Light, Arbor Elf, Argothian Swine,
Armored Griffin, Asha's Favor, Baleful Strix, Bay Falcon, Bayou Dragonfly,
Bladed Sentinel, Blockade Runner, Blood Pet, Bog Smugglers, Boreal Druid,
Bound in Silence, Canopy Cover, Cathedral of War, Cat Warriors,
Cavern Crawler, Cemetery Gate, Cerulean Wyvern, Cloak of Mists,
Cloudheath Drake, Colos Yearling, Coma Veil, Council of Advisors,
Crippling Blight, Cursed Flesh, Death-Hood Cobra, Death Speakers,
Deeptread Merrow, Defender of Chaos, Defender of Law, Dehydration,
Delusions of Mediocrity, Devouring Deep, Diplomatic Immunity,
Dirtwater Wraith, Dragon Hatchling, Duergar Cave-Guard, Duskmantle Prowler,
Duskrider Falcon, Duty-Bound Dead, Edge of the Divinity, Elite Cat Warrior,
Elvish Lookout, Enslaved Scout, Eternal Warrior, Eternity Snare, Eviscerator,
Eye of Ramos, Faerie Invaders, Fear, Flaming Sword, Flowstone Crusher,
Flowstone Giant, Flowstone Hellion, Flowstone Mauler, Flowstone Shambler,
Flowstone Wall, Flowstone Wyvern, Freewind Falcon, Frog Tongue,
Furor of the Bitten, Geth's Verdict, Ghostly Changeling, Giant Crab,
Giant Mantis, Gift of Granite, Gigadrowse, Glimmerdust Nap, Glimmering Angel,
Gnat Alley Creeper, Greel's Caress, Guardian Lions, Guma, Hawkeater Moth,
Hazerider Drake, Heart of Ramos, Heartwood Treefolk, Highland Giant,
Horn of Ramos, Ichor Wellspring, Ihsan's Shade, Illusions of Grandeur,
Indomitable Will, Intrepid Hero, Jhovall Queen, Jungle Barrier,
Kabira Crossroads, Karoo Meerkat, Kavu Glider, Keen-Eyed Archers,
Keeper of Kookus, Knight of Glory, Knight of Infamy, Knight of Stromgald,
Kyren Glider, Lance, Leaden Fists, Leap of Flame, Lionheart Maverick,
Llanowar Cavalry, Lost Soul, Lowland Giant, Loxodon Stalwart, Lucent Liminid,
Lynx, Magefire Wings, Mageta's Boon, Maggot Therapy, Maniacal Rage,
Manta Riders, Mark of the Vampire, Marsh Goblins, Mask of Law and Grace,
Melesse Spirit, Merchant of Secrets, Moonglove Changeling, Moor Fiend,
Mountain Goat, Mox Emerald, Mox Jet, Mox Pearl, Mox Ruby, Mox Sapphire,
Mycoloth, Mythic Proportions, Nightwind Glider, Nimbus Wings, Norwood Archers,
Obsidian Giant, Oraxid, Order of Leitbur, Order of the Ebon Hand,
Order of the White Shield, Orim's Thunder, Pale Bears, Paralyzing Grasp,
Pendelhaven, Pestilent Kathari, Phyresis, Plated Slagwurm, Plated Spider,
Primal Frenzy, Primal Huntbeast, Pygmy Allosaurus, Pyromatics, Reckless Brute,
Reflexes, Repentant Blacksmith, Rib Cage Spider, Ridgeline Rager,
Righteous Avengers, River Merfolk, Savannah Lions, Scalebane's Elite,
Scavenged Weaponry, Scragnoth, Sea Sprite, Segovian Leviathan,
Sentinel Spider, Serra Sphinx, Servant of Nefarox, Shattering Spree,
Shield of Duty and Reason, Shore Snapper, Sighted-Caste Sorcerer,
Skull of Ramos, Slippery Bogle, Spiked Baloth, Steam Spitter, Striped Bears,
Stromgald Crusader, Stronghold Zeppelin, Taoist Hermit, Tempest Drake,
Thermal Glider, Tiger Claws, Tooth of Ramos, Train of Thought,
Treetop Bracers, Tricks of the Trade, Vacuumelt, Vectis Silencers, Vigilance,
Voice of Duty, Voice of Grace, Voice of Law, Voice of Reason, Voice of Truth,
Vulshok Sorcerer, Wall of Light, Wall of Tanglecord, Warclamp Mastiff,
War-Spike Changeling, Warthog, Watercourser, Weatherseed Faeries, Web,
Whispering Shade, White Shield Crusader, Wild Cantor, Wildfire Emissary,
Wild Ox, Willow Dryad, Windreaper Falcon, Windseeker Centaur,
Wings of Aesthir, Wings of Hope, Wistful Selkie, Zephid, Zephid's Embrace,
Zephyr Falcon, Zephyr Net, Zodiac Ox, Zodiac Pig, Zodiac Rat, Zodiac Rooster,
Zodiac Snake, Zodiac Tiger

Release 1.28 (July 29, 2012)
============
includes contributions from mecheng, melvin, pcastellazzi, and sponeta 

- fixed: Jungle Troll not considered red
- fixed: Ulvenwald Bear's was using Festerhide Boar's ability
- fixed: Nibilis of the Breath's ability cost {U} not {W}
- fixed: Havengul Vampire gets -1/-1 instead of +1/+1
- fixed: Malignus' power and toughness

- added the following premade decks:
  BG_Sniper_40.dec
  BU_Gluttony_40.dec
  BU_Gluttony_60.dec
  BU_Mind_Mangler_40.dec
  BU_Mind_Mangler_60.dec
  BU_Nightmare_Control_40.dec
  GW_Samurai_40.dec
  RB_Vampire_Count_40.dec
  RG_Rush_40.dec
  RW_Flash_40.dec
  RW_Holy_Fire_40.dec
  Rainbow_Dragonlord_60.dec
  Relentless_Rats.dec
  WB_Spirit_Tokens_40.dec
  W_Holy_Arms_40.dec

- added the following cards:
  Badlands
  Bayou
  Goblin King
  Granite Gargoyle
  Plateau
  Roc of Kher Ridges
  Savannah
  Scrubland
  Scryb Sprites
  Tropical Island
  Tundra
  Underground Sea
  Volcanic Island
  Wall of Fire

Release 1.27 (July 1, 2012)
============
- added support for "miracle <mana cost>" in ability property of card script
- added support for "{X}{X}" mana cost
- added decks Glorfindel-Bant-Aggro.dec, Glorfindel_GWR_Zoo.dec, and
  Glorfindel_G_Infect_40.dec

- improved support for control changing effects

- fixed: Geist of Saint Traft and Edric, Spymaster of Trest not Legendary
- fixed: Imps' Taunt has wrong converted mana cost
- fixed: "Generate Deck" button loads a random saved deck

- added the following cards:
Ageless Sentinels, Beguiler of Wills, Binding Grasp, Biting Tether,
Bloodflow Connoisseur, Bonfire of the Damned, Burning Inquiry,
Butcher Ghoul, Call to Serve, Captain of the Mists, Cathars' Crusade,
Cloudshift, Commander's Authority, Confiscate, Conjurer's Closet,
Control Magic, Corrupted Conscience, Craterhoof Behemoth, Crippling Chill,
Crypt Creeper, Cursebreak, Death Wind, Defang, Defy Death, Demonic Rising,
Demonic Taskmaster, Demonlord of Ashmouth, Desolate Lighthouse,
Devastation Tide, Diregraf Escort, Drake-Skull Cameo, Dreadwaters,
Driver of the Dead, Druid's Familiar, Dungeon Geists, Eaten by Spiders,
Elder Land Wurm, Elgaud Shieldmate, Emancipation Angel, Enslave,
Entreat the Angels, Essence Harvest, Evernight Shade, Exquisite Blood,
Falkenrath Exterminator, Farbog Explorer, Favorable Winds, Fervent Cathar,
Fettergeist, Firescreamer, Fleeting Distraction, Geist Snatch, Geist Trappers,
Ghoulflesh, Gloom Surgeon, Goldnight Commander, Goldnight Redeemer,
Grave Exchange, Grounded, Gryff Vanguard, Guise of Fire, Hanweir Lancer,
Harvester of Souls, Haunted Guardian, Havengul Skaab, Havengul Vampire,
Heirs of Stromkirk, Holy Justiciar, Homicidal Seclusion, Hooded Kavu,
Hound of Griselbrand, Human Frailty, Joint Assault, Kessig Malcontents,
Kruin Striker, Latch Seeker, Leap of Faith, Lightning Mauler, Lunar Mystic,
Maalfeld Twins, Mad Prophet, Malignus, Mark of the Oni, Mass Appeal,
Master Thief, Mental Agony, Midnight Duelist, Mind Control, Mirrorworks,
Mist Raven, Moonsilver Spear, Moorland Inquisitor, Narstad Scrapper,
Natural End, Nearheath Pilgrim, Necrobite, Nephalia Smuggler,
Nettle Swine, Nightshade Peddler, Noble Panther, Otherworld Atlas,
Outwit, Pathbreaker Wurm, Persuasion, Phyrexian Walker, Polluted Dead,
Predator's Gambit, Prodigal Sorcerer, Raging Poltergeist, Reforge the Soul,
Renegade Demon, Restoration Angel, Righteous Blow, Riot Ringleader,
Roil Elemental, Rotcrown Ghoul, Rush of Blood, Scalding Devil, Scrapskin Drake,
Scroll of Avacyn, Scroll of Griselbrand, Searchlight Geist, Seraph of Dawn,
Seraph Sanctuary, Serpentine Kavu, Slayers' Stronghold, Somberwald Vigilante,
Soulcage Fiend, Soul of the Harvest, Spectral Gateguards, Spirit Away,
Taiga, Temporal Mastery, Terminus, Thatcher Revolt, Thraben Valiant,
Thunderous Wrath, Timberland Guide, Time Walk, Triumph of Cruelty,
Triumph of Ferocity, Trusted Forcemage, Uncanny Speed, Undead Executioner,
Unhallowed Pact, Vanishment, Vessel of Endless Rest, Vigilante Justice,
Voice of the Provinces, Vorstclaw, Wall of Blossoms, Wheel of Fortune,
Wild Defiance, Wildwood Geist, Yavimaya's Embrace, Yew Spirit, Zealous Strike,

Release 1.26 (May 26, 2012)
============
- added decks Black_and_White.dec, Haunted_Humans.dec, Lingering_Spirits.dec,
  and Token_Humans.dec by Old Nick
- added support for Soulbond in card script
- added player name to game.log

- changed card script so that card name is described using "name=" instead of ">"
- improved exception handling so that crash.log captures the relevant game state

- fixed: generate deck does not use user's settings (issue 213) 
- fixed: Scorch the Fields not dealing damage to all humans (issue 214)
- fixed: program crash when loading Fallen_Knights_60.dec due to missing comment symbol
- fixed: card image was not downloaded even though current image should be ignored
- fixed: cost of Torch Fiend's ability should be {R} instead of {1}{R}
- fixed: crash bug due to Ulamog and Kozilek's second ability

- added the following cards:
  Aggravate
  Alchemist's Apprentice
  Angel of Glory's Rise
  Angel's Tomb
  Angelic Armaments
  Archangel
  Archwing Dragon
  Astral Steel
  Avacyn, Angel of Hope
  Banners Raised
  Barbarian Riftcutter
  Barter in Blood
  Bladed Bracers
  Blood Artist
  Brain Freeze
  Brimstone Volley
  Cathedral Sanctifier
  Custody Battle
  Demigod of Revenge
  Devout Monk
  Dingus Egg
  Dissipate
  Dwarven Driller
  Grapeshot
  Jedit's Dragoons
  Kingfisher
  Oculus
  Peace Strider
  Ratchet Bomb
  Rhox Bodyguard
  Riptide Crab
  Shu Grain Caravan
  Shu Soldier-Farmers
  Silence
  Silverblade Paladin
  Spiritual Guardian
  Staunch Defenders
  Stone Rain
  Temple Acolyte
  Teroh's Faithful
  Tireless Missionaries
  Wasteland
  Wingcrafter
  Wolfir Avenger
  Wolfir Silverheart
  Xantid Swarm
  Zealous Conscripts

Release 1.25 (April 29, 2012)
============
- fixed unreachable buttons on duel panel for smaller screens
- fixed bug with exalted by making the declare attackers step follow the rules
  more closely
- improved Linux launch script thanks to suggestion from pablo.castellazzi
- improved Minimax AI

Release 1.24 (March 31, 2012)
============
- added mulligan
- added basic game log
- added support for using last known information
- added two decks (BU_Card_Advantage.dec and Goblin_Sacrifice_40.dec)
- added support for Bloodthirst ability
- added "enchant" property to card script
- generalized "landfall pump" ability in card script to allow different power/toughness
- added "enters with +1/+1 for each kick <mana cost>" to card script
- added support for Annihilator ability

- fixed: casting cost of Crimson Mage was {R}, should be {1}{R})
- fixed: automatic paying of mana cost not taking into account charge counters
- fixed: ability granted by Livewire Lash doesn't obey protection-based targeting restrictions
- fixed: wrath effects interact incorrectly with Knight Exemplar
- fixed: token getting shuffled into the library
- fixed: morbid trigger from Reaper from the Abyss doesn't target creatures you own
- fixed: copying Ravaging Riftwurm doesn't put counters on it when it enters the battlefield
- fixed: Fiend Hunters ability was not targeted
- fixed: Arcbound Overseer's upkeep trigger doesn't work
- fixed: Telim'Tor gives a bonus to every attacking creature
- fixed: Glint Hawk's ability was targeted, but it should be non targeted
- fixed: A creature with shroud is offered as target for Faceless Butcher's effect.
- fixed: Faceless Devourer's ability was not targeted
- fixed: Leonin Relic-Warder's ability was not targeted
- fixed: Fumiko the Lowblood doesn't get bushido credit from Hero of Bladehold's new soldiers

- added the following cards:
Acorn Catapult, Alexi's Cloak, Ancient Silverback, Antagonism, Archangel's
Light, Artisan of Kozilek, Avacyn's Collar, Bar the Door, Battle Mastery,
Blisterstick Shaman, Blood Ogre, Bloodrage Vampire, Bloodscale Prowler,
Bloodshed Fever, Bogardan Lancer, Bone to Ash, Briarpack Alpha, Buoyancy,
Burden of Guilt, Cantivore, Carnage Wurm, Carnassid, Carrion Wall, Chant of
the Skifsang, Cinderbones, Clay Statue, Clinging Mists, Cognivore, Corrupt
Eunuchs, Darkling Stalker, Darksteel Ingot, Deranged Outcast, Divine
Transformation, Drogskol Captain, Drogskol Reaver, Drowned, Druid's Call,
Duskhunter Bat, Eland Umbra, Elgaud Inquisitor, Enfeeblement, Eron the
Relentless, Executioner's Hood, Favor of the Woods, Feast of the Unicorn,
Feebleness, Fire Imp, Flowstone Charger, Fog of Gnats, Furyborn Hellkite,
Gather the Townsfolk, Gavony Ironwright, Geralf's Mindcrusher, Ghor-Clan
Savage, Ghost Ship, Giant Strength, Goblin Commando, Gorehorn Minotaurs,
Goretusk Firebeast, Gorilla Chieftain, Gravetiller Wurm, Grim Backwoods, Grim
Flowering, Griptide, Gristleback, Gwafa Hazid, Profiteer, Havengul Runebinder,
Heavy Mattock, Hollow Dogs, Hollowhenge Beast, Hollowhenge Spirit, Horned
Troll, Hunger of the Howlpack, Jungle Troll, Kessig Recluse, Kiln Walker,
Kozilek, Butcher of Truth, Krosan Beast, Lim-Dul's High Guard, Living Airship,
Living Wall, Lurking Crocodile, Lurking Nightstalker, Magnivore, Malach of the
Dawn, Mammoth Umbra, Metathran Zombie, Midnight Guard, Nephalia Seakite,
Niblis of the Breath, Niblis of the Mist, Niblis of the Urn, Odious Trow,
Pewter Golem, Phyrexian Monitor, Phyrexian Obliterator, Rabble-Rouser, Ranger
en-Vec, Ravenous Skirge, Requiem Angel, Restless Dead, Revered Dead, Sanctuary
Cat, Sanguine Guard, Scab-Clan Mauler, Screeching Harpy, Screeching Skaab,
Seance, Shriekgeist, Silverclaw Griffin, Skeletal Changeling, Skeletal Wurm,
Skillful Lunge, Skyshroud Troll, Slith Ascendant, Slith Bloodletter, Slith
Firewalker, Slith Predator, Somberwald Dryad, Spectral Lynx, Squirrel Mob,
Stormbound Geist, Sudden Disappearance, Tangle Hulk, Tarmogoyf, Tattered
Drake, Tel-Jilad Exile, Terravore, Thought Scour, Thraben Doomsayer, Toxic
Nim, Tragic Slip, Ulamog's Crusher, Ulamog, the Infinite Gyre, Ulvenwald Bear,
Unworthy Dead, Uthden Troll, Vampire Outcasts, Vault of the Archangel,
Vengeful Vampire, Vicious Kavu, Village Survivors, Votary of the Conclave,
Wakedancer, Walking Dead, Wall of Brambles, Wall of Pine Needles, War
Elemental, Wei Ambush Force, Will-o'-the-Wisp, Yavimaya Gnats, Young Wolf,
Zombie Apocalypse

Release 1.23 (February 25, 2012)
============
- added double-clicking right mouse button in deck pane adds a copy of selected card to deck
- added drawing of counters for every permanent instead of for creatures only
- added drawing of +1/+1 and -1/-1 counters in graphics mode
- changed splash screen to show on all platforms
- added saving a description to a deck
- added 8 new decks

- fixed: skip choosing of mana source when mana cost equals total amount of sources
- fixed: running the deck strength calculator updates history
- fixed: Curiosity only triggers on combat damage
- fixed: Mistbind Clique can only champion Faerie creatures instead of every Faerie subtype
- fixed: Oblivion Ring's ability can target cards that have shroud
- fixed: Blade of the Bloodchief doesn't check if it's equipped
- fixed: Dread (and other Incarnations) doesn't get reshuffled into library when milled
- fixed: when Platinum Angel is in play, reducing opponent to 0 life isn't sufficient to win the game
- fixed: Avacynian Priest can tap any non-human permanent instead of non-human creatures.
- fixed: when at exactly 1 life, shocklands come into play untapped
- fixed: Vindicate has the image of Orim's Chant
- fixed: AEther Vial and Legacy's Allure's abilities missing
- fixed: Nephalia Drownyard can tap itself for mana while using its ability

- added the following cards:
  Alpha Brawl
  Ancestral Recall
  Black Cat
  Boggart Shenanigans
  Break of Day
  Death's Caress
  Diregraf Captain
  Endless Wurm
  Erdwal Ripper
  Ertai, Wizard Adept
  Falkenrath Aristocrat
  Falkenrath Torturer
  Farbog Boneflinger
  Forge Devil
  Geralf's Messenger
  Goblin Bombardment
  Harrowing Journey
  Heckling Fiends
  Hellrider
  Highborn Ghoul
  Ib Halfheart Goblin Tactician
  Jayemdae Tome
  Kamahl, Fist of Krosa
  Knucklebone Witch
  Mad Auntie
  Markov Blademaster
  Mikaeus, the Unhallowed
  Moonveil Dragon
  Nearheath Stalker
  Rhox
  Russet Wolves
  Scorch the Fields
  Sightless Ghoul
  Skirsdag Flayer
  Spiteful Shadows
  Strangleroot Geist
  Stromkirk Captain
  Subversion
  Torch Fiend
  Vorapede
  Weatherseed Treefolk
  Wrack with Madness

Release 1.22 (January 28, 2012)
============
- improved resolution of Mac icon
- added option to use double-click to cast or activate ability (for touchscreens)
- added 17 new decks

- fixed: Eldrazi Spawn token can not be sacrificed for mana
- fixed: Death Baron also gives +1/+1 to opponent's creatures
- fixed: AI uses Rise of the Hobgoblins' activated ability more than once per turn
- fixed: Wall of Diffusion can not block creatures with shadow
- fixed: application exit through CMD-Q is not captured on OS X

- known bug: AEther Vial's and Legacy's Allure's abilities don't work

Release 1.21 (December 26, 2011)
============
brought to you by beholder, melvin, and wait321

- added zachsoulsister deck
- added feather and gold counters to be shown in image mode
- added number of cards to cube names in combobox
- enabled the option to save a deck between matches
- added shortcut keys for Yes and No buttons
- added confirmation dialog on exit
- added basic historical overview
- added Online Documentation item to Help menu

- fixed: Scion of Oona is colorless
- fixed: Ruham of the Fomori is colorless
- fixed: Volcanic Hammer is an instant instead of a sorcery
- fixed: Pulse of the Fields is a sorcery instead of an instant
- fixed: Gemstone Mine doesn't remove a counter when tapped for mana
- fixed: Woodland Sleuth's Morbid ability triggers even if no creatures died this turn
- fixed: Joraga Warcaller
- fixed: animated permanents don't lose temporary buffs at end of turn
- fixed: cards that deal damage when they enter or leave play can show a wrong description
- fixed: echo costs interact with each other and therefor can be wrong
- fixed: Roughshod Mentor doesn't give trample to itself
- fixed: cards that have their payback cost payed return to hand instead of graveyard
  when there was no legal target
- fixed: Righteousness has the picture of Giant Growth

- added the following cards:
  Blighted Agent
  Charging Bandits
  Charging Paladin
  Corrupted Resolve
  Deadly Grub
  Untamed Might
  Vapor Snag

Release 1.20b (November 26, 2011)
=============
This is a bug fix release.

- fixed: opening the Keywords/Readme screen would cause keyboard shortcuts to stop working
- fixed: creatures with scripted abilities were getting the wrong ones due to an engine bug
- fixed: Vivid land loses counter even when tapping for the right mana
- fixed: description of Searing Touch says it deals 3 damage when it should be only 1 damage

Release 1.20 (November 25, 2011)
============
brought to you by beholder, melvin, and wait321

- added option to automatically copy images from old data folder in the
  "Download images" dialog
- added discard down to 7 cards at end of turn
- added support for Echo keyword
- corrected all rarities
- combined duel difficulty sliders with duel progress window
- tweaked AI handling of blocking options
- card scripts can be added/changed without needing to compile the source code
- more abilities can be scripted

- fixed: Grimoire of the Dead doesn't tap when activating its ability
- fixed: Isao, Enlightened Bushi should regenerate target instead of itself
- fixed: error in card statistics
- fixed: Kor Firewalker doesn't have protection from red
- fixed: Merfolk Seastalkers' activated ability doesn't work
- fixed: Cursed Ronin has regenate ability instead of pump ability
- fixed: taking control of a creature ignores summoning sickness rule
- fixed: Necropede's subtype is Myr instead of Insect
- fixed: Victim of Night uses picture of Doom Blade
- fixed: Death Baron does not give deathtouch
- fixed: Ballista Squad sacrifices itself
- fixed: Benalish Lancer doesn't get two +1/+1 counters and first strike when kicked
- fixed: Sparkmage Apprentice can only deal damage to creatures

- added the following cards:
Abyssal Nightstalker, Acridian, Akoum Battlesinger, Albino Troll, Ardent
Recruit, Auriok Glaivemaster, Avalanche Riders, Basalt Gargoyle, Battlegrowth,
Black Vise, Bloodhall Ooze, Body of Jukai, Bojuka Brigand, Bone Dancer, Bone
Shredder, Burr Grafter, Canker Abomination, Carrion Ants, Citanul Centaurs,
Concussive Bolt, Cradle Guard, Crater Hellion, Crawling Filth, Crypt Cobra,
Deathknell Kami, Deepcavern Imp, Deranged Hermit, Dwarven Vigilantes, Echoing
Calm, Echoing Courage, Echoing Decay, Echoing Truth, Enclave Cryptologist,
Evincar's Justice, Extruder, Fanning the Flames, Farrel's Zealot, Fevered
Convulsions, Firemaw Kavu, Flamecore Elemental, Floral Spuzzem, Forked-Branch
Garami, Gemstone Mine, Ghitu Slinger, Gibbering Kami, Gnarled Effigy, Goblin
Marshal, Goblin Patrol, Goblin War Buggy, Graypelt Hunter, Guiltfeeder, Hada
Freeblade, Hagra Diabolist, Halimar Excavator, Halimar Wavewatch, Hammerheim
Deadeye, Hedron-Field Purists, Henchfiend of Ukor, Herald of Serra, Highland
Berserker, Hundred-Talon Kami, Hunting Moa, Ikiral Outrider, Imps' Taunt, Join
the Ranks, Joraga Bard, Kabira Vindicator, Kami of Empty Graves, Kami of
Lunacy, Kami of the Honored Dead, Kami of the Palace Fields, Kami of the
Tended Garden, Kargan Dragonlord, Karmic Guide, Kazandu Blademaster, Kazandu
Tuskcaller, Kazuul Warlord, Keldon Champion, Keldon Vandals, Knight Errant,
Knight of Cliffhaven, Kodama of the Center Tree, Lab Rats, Laccolith Grunt,
Laccolith Titan, Laccolith Warrior, Laccolith Whelp, Lagac Lizard, Leonin
Den-Guard, Lighthouse Chronologist, Lightning Dragon, Lim-Dul the Necromancer,
Makindi Shieldmate, Mind Games, Mind Peel, Mogg War Marshal, Multani's
Acolyte, Murasa Pyromancer, Nightsoil Kami, Nimana Sell-Sword, Norwood Ranger,
Null Champion, Ondu Cleric, Oran-Rief Survivalist, Pouncing Jaguar, Promised
Kannushi, Pus Kami, Radiant's Dragoons, Raksha Golden Cub, Razorfield Rhino,
Reiterate, Rootrunner, Scuttling Death, Sea Gate Loremaster, Searing Touch,
Seascape Aerialist, Seething Anger, Shanodin Dryads, Shattering Pulse, Shivan
Raptor, Simian Grunts, Skyhunter Cub, Skywatcher Adept, Smallpox, Spiraling
Duelist, Stingscourger, Subterranean Shambler, Sunspear Shikari, Suq'Ata
Assassin, Swamp Mosquito, Tajuru Archer, Talus Paladin, Tectonic Fiend,
Thalakos Deceiver, The Rack, Thief of Hope, Thousand-legged Kami, Thran War
Machine, Ticking Gnomes, Timbermare, Torii Watchward, Transcendent Master,
Tuktuk Grunts, Tuktuk Scrapper, Turntimber Ranger, Uktabi Drake, Umara Raptor,
Urza's Blueprints, Venerable Kumo, Viashino Outrider, Vug Lizard, Winding
Wurm, Wretched Banquet, Zealot il-Vec

Release 1.19 (October 28, 2011)
============
brought to you by beholder, melvin, and wait321
 
- improved AI performance when there are a lot of blocking options
- downloaded images are updated automatically. No need to restart Magarena
- improved AI handling of equipment
- improved AI's usage of abilities that can be activated multiple times
- added basic deck construction rule checking
- added full card text searching. Text is downloaded with the images
- added support for morbid ability
- added support for modular ability
- added support to generate tribal/theme decks
- added support for "when becomes blocked" trigger
- added support for "when leaves play" trigger
- added support for shadow ability
- added support for can't be blocked by a color ability
- added Affinity and Finkel_Fun decks

- fixed: static effects that change or add colors don't work
- fixed: game crashes because of creating components in non event dispatching thread
- fixed: Torpor Dust gives flash ability instead of having flash ability
- fixed: game crashes when trying to update the card images after images are downloaded
- fixed: state-based actions are checked after every event instead of whenever
         a player would get priority and during cleanup step
- fixed: creatures that are enchanted so that they cannot attack were able to attack
- fixed: undoing "gain control until end of turn" effect causes permanent to
         be controlled indefinitely
- fixed: permanent that becomes an artifact is not recognized as such by the game
- fixed: Goldenglow Moth doesn't have flying ability
- fixed: Angelheart Vial doesn't tap when activating its ability
- fixed: Hypnotic Specter's ability triggers for its controller
- fixed: Magarena crashes when using java 7
- fixed: Eel Umbra doesn't have flash ability
- fixed: Windrider Eel is black but should be blue
- fixed: Elesh Norn, Grand Cenobite gives itself a bonus

- added the following 541 cards:
Abbey Griffin, Abuna Acolyte, Abyssal Specter, Academy Ruins, Acid Web Spider,
Admonition Angel, AErathi Berserker, AEtherflame Wall, AEther Membrane, AEther
Vial, AEther Web, Algae Gharial, Allay, Alley Grifters, Ambush Viper,
Amphibious Kavu, Ancient Den, Ancient Hydra, Angelic Overseer, Angel of Flight
Alabaster, Anoint, Apex Hawks, Apocalypse Hydra, Araba Mothrider, Arcbound
Bruiser, Arcbound Crusher, Arcbound Fiend, Arcbound Hybrid, Arcbound Lancer,
Arcbound Overseer, Arcbound Ravager, Arcbound Reclaimer, Arcbound Slith,
Arcbound Stinger, Arcbound Worker, Arctic Nishoba, Arctic Wolves, Ardent
Soldier, Argent Sphinx, Argentum Armor, Armored Skaab, Artifact Mutation,
Ashmouth Hound, Assault Strobe, Augur il-Vec, Aurification, Auriok Edgewright,
Auriok Sunchaser, Avacynian Priest, Avacyn's Pilgrim, Avenger of Zendikar,
Aven Riftwatcher, Balduvian War-Makers, Balefire Dragon, Barrage Ogre,
Barrenton Cragtreads, Battered Golem, Battleground Geist, Battle-Mad Ronin,
Beastbreaker of Bala Ged, Beastmaster's Magemark, Benalish Cavalry, Benalish
Lancer, Berserk Murlodont, Blade Splicer, Blade-Tribe Berserkers, Blastoderm,
Bleak Coven Vampires, Blight Mamba, Blinking Spirit, Blistergrub, Bloodcrazed
Neonate, Bloodgift Demon, Bloodhusk Ritualist, Bloodshot Trainee, Blunt the
Assault, Boggart Mob, Bonds of Quicksilver, Bonehoard, Boneyard Wurm, Brain
Weevil, Bramblecrush, Brimstone Mage, Brushwagg, Brush with Death, Burning
Shield Askari, Butcher's Cleaver, Cadaverous Knight, Calciderm, Calcite
Snapper, Capsize, Carapace Forger, Caravan Escort, Carrion Call, Caustic
Crawler, Cave Tiger, Chambered Nautilus, Champion of the Parish, Changeling
Berserker, Changeling Hero, Changeling Titan, Change of Heart, Chapel Geist,
Charmbreaker Devils, Chilling Apparition, Chrome Steed, Chronozoa, Chub Toad,
Claustrophobia, Clifftop Retreat, Close Quarters, Cloudskate, Cobbled Wings,
Copperhorn Scout, Copper Myr, Coralhelm Commander, Corpse Cur, Corpse Dance,
Corrupted Harvester, Corrupt Official, Cosi's Ravager, Craw Giant, Crossway
Vampire, Culling Dais, Curiosity, Cursed Ronin, Cutthroat il-Dal, Darksteel
Citadel, Darksteel Juggernaut, Darkthicket Wolf, Dauthi Cutthroat, Dauthi
Embrace, Dauthi Ghoul, Dauthi Horror, Dauthi Marauder, Dauthi Mercenary,
Dauthi Slayer, Dauthi Trapper, Dauthi Warlord, Day of the Dragons, Dead
Weight, Deadwood Treefolk, Deathforge Shaman, Deathgazer, Deathgreeter,
Deep-Slumber Titan, Deepwood Tantiv, Deepwood Wolverine, Defender en-Vec,
Desert, Devoted Retainer, Diregraf Ghoul, Disciple of Griselbrand, Disenchant,
Disperse, Disturbed Burial, Doomed Traveler, Dread Specter, Dread Statuary,
Drelnoch, Drifter il-Dal, Dromosaur, Dross Hopper, Duskwalker, Duskworker,
Dwarven Berserker, Earthen Goo, Echoing Ruin, Elder Cathar, Elder of Laurels,
Elite Inquisitor, Elven Warhounds, Elvish Berserker, Elvish Fury, Embersmith,
Emeria Angel, Enclave Elite, Endless Ranks of the Dead, Engulfing Slagwurm,
Escaped Null, Etched Champion, Exhume, Ezuri, Renegade Leader, Ezuri's
Brigade, Faceless Butcher, Faceless Devourer, Faerie Conclave, Faerie
Squadron, Falkenrath Marauders, Falkenrath Noble, Fallen Askari, Femeref
Knight, Feral Ridgewolf, Ferocity, Ferrovore, Festerhide Boar, Fiend Hunter,
Firestorm Hellkite, Fledgling Griffin, Forbidding Watchtower, Fortress Crab,
Frightful Delusion, Frost Giant, Fumiko the Lowblood, Gallows Warden, Galvanic
Juggernaut, Gang of Elk, Gavony Township, Geistcatcher's Rig, Geist-Honored
Monk, Geist of Saint Traft, Geyser Glider, Ghalma's Warden, Ghitu Encampment,
Ghoulraiser, Glint Hawk, Glint Hawk Idol, Glissa's Scorn, Glissa, the Traitor,
Gnarlid Pack, Goblin Burrows, Goblin Lackey, Goblin Sharpshooter, Gods' Eye,
Gate to the Reikai, Golden Urn, Gold Myr, Golem Foundry, Golem's Heart, Grave
Bramble, Grave Pact, Graveyard Shovel, Grazing Gladehart, Great Furnace,
Grimgrin, Corpse-Born, Grimoire of the Dead, Gustcloak Cavalier, Gustcloak
Harrier, Gustcloak Runner, Gustcloak Savior, Gustcloak Sentinel, Gustcloak
Skirmisher, Guul Draz Assassin, Hada Spy Patrol, Hagra Crocodile, Halt Order,
Hamlet Captain, Hand of Cruelty, Hand of Honor, Heartwood Dryad, Hedron Crab,
Hedron Rover, Hedron Scrabbler, Hinterland Harbor, Hoard-Smelter Dragon,
Hollowhenge Scavenger, Horrible Hordes, Hunding Gjornersen, Hysterical
Blindness, Ichorclaw Myr, Ichor Rats, Iizuka the Ruthless, Illusionary Forces,
Illusionary Wall, Immolation, Imperious Perfect, Indebted Samurai, Indomitable
Archangel, Inferno Elemental, Infiltration Lens, Inner-Chamber Guard, Instill
Infection, Intangible Virtue, Invisible Stalker, Ior Ruin Expedition, Iron
Myr, Isao, Enlightened Bushi, Isolated Chapel, Jolrael's Centaur, Jolting
Merfolk, Jotun Owl Keeper, Journey to Nowhere, Kaijin of the Vanishing Touch,
Karn, Silver Golem, Kavu Aggressor, Keldon Marauders, Kemba, Kha Regent,
Kemba's Skyguard, Kessig Cagebreakers, Kessig Wolf, Kessig Wolf Run, Khalni
Garden, Kindercatch, Kitsune Blademaster, Kitsune Dawnblade, Kjeldoran
Javelineer, Konda, Lord of Eiganjo, Konda's Hatamoto, Kresh the Bloodbraided,
Kuro's Taken, Lantern Spirit, Lavacore Elemental, Leaden Myr, Legacy's Allure,
Leonin Relic-Warder, Lifesmith, Lightning Crafter, Livewire Lash, Llanowar
Elite, Looter il-Kor, Lowland Basilisk, Lumberknot, Lumengrid Drake, Make a
Wish, Manor Skeleton, Marhault Elsdragon, Markov Patrician, Mask of Avacyn,
Master Splicer, Maul Splicer, Mausoleum Guard, Maw of the Mire, Melt Terrain,
Mentor of the Meek, Midnight Haunting, Mikaeus, the Lunarch, Mindshrieker,
Mistbind Clique, Molder Beast, Moldgraf Monstrosity, Moment of Heroism, Moon
Heron, Morkrut Banshee, Mothrider Samurai, Mtenda Herder, Mudbrawler Raiders,
Murder of Crows, Mwonvuli Ooze, Myr Galvanizer, Myr Propagator, Myrsmith,
Nagao, Bound by Honor, Nantuko Monastery, Necrogen Censer, Necrogen Scudder,
Necropede, Nephalia Drownyard, Nettle Sentinel, Nezumi Ronin, Night Revelers,
Norwood Warrior, Nova Chaser, Nyxathid, Oblivion Ring, Odylic Wraith, Ogre
Leadfoot, One-Eyed Scarecrow, Ophidian, Orchard Spirit, Order of Yawgmoth,
Oxidda Scrapmelter, Oxidize, Parallax Nexus, Parallax Tide, Parallax Wave,
Paraselene, Petravark, Phobian Phantasm, Phyrexian Bloodstock, Phyrexian
Prowler, Phyrexian Reaper, Phyrexian Slayer, Pincer Spider, Pitchburn Devils,
Pouncing Kavu, Pouncing Wurm, Proper Burial, Pygmy Troll, Quag Vampires, Rabid
Elephant, Rabid Wolverines, Rage Thrower, Raging Gorilla, Rakish Heir,
Ravaging Riftwurm, Raven's Run Dragoon, Razorclaw Bear, Reality Acid, Realm
Razer, Reaper from the Abyss, Rebuke, Recurring Nightmare, Regrowth,
Rejuvenation Chamber, Revered Unicorn, Riot Devils, Rock Basilisk, Rockslide
Elemental, Ronin Cavekeeper, Ronin Cliffrider, Ronin Houndmaster, Rotting
Fensnake, Rusting Golem, Sacred Knight, Sacred Prey, Samurai Enforcers,
Saprazzan Heir, Scavenger Drake, Scourge of Geier Reach, Screeching Silcaw,
Scrib Nibblers, Seat of the Synod, Seer's Sundial, Selfless Cathar, Selhoff
Occultist, Sensor Splicer, Sensory Deprivation, Shadow Rider, Shadow Rift,
Shadowstorm, Sharpened Pitchfork, Sidar Jabari, Silkenfist Fighter, Silkenfist
Order, Silverchase Fox, Silver-Inlaid Dagger, Silver Myr, Silverstorm Samurai,
Skarrg, the Rage Pits, Skirsdag Cultist, Skitter of Lizards, Skyshroud
Behemoth, Skyshroud Ridgeback, Slashing Tiger, Slayer of the Wicked, Slinking
Giant, Slith Strider, Sludge Strider, Smite the Monstrous, Snapping Creeper,
Snapsail Glider, Snorting Gahr, Sokenzan Spellblade, Soltari Champion, Soltari
Crusader, Soltari Emissary, Soltari Foot Soldier, Soltari Lancer, Soltari
Monk, Soltari Priest, Soltari Trooper, Soltari Visionary, Somberwald Spider,
Sootwalkers, Sosuke, Son of Seshiro, Soultether Golem, Sparring Golem,
Spectral Flight, Spectral Rider, Spidery Grasp, Splinterfright, Stensia
Bloodhall, Stitcher's Apprentice, Stromkirk Noble, Stromkirk Patrol,
Stronghold Overseer, Stronghold Rats, Sturmgeist, Sulfur Falls, Sunhome,
Fortress of the Legion, Sunspring Expedition, Supreme Exemplar, Suq'Ata
Lancer, Surrakar Marauder, Swarmyard, Sylvan Basilisk, Takeno's Cavalry,
Talruum Champion, Tangle Asp, Teeka's Dragon, Telim'Tor, Tel-Jilad Wolf,
Temporal Isolation, Territorial Baloth, Thalakos Drifters, Thalakos Scout,
Thalakos Seer, Thalakos Sentry, Thicket Basilisk, Thraben Purebloods, Thresher
Beast, Tidewalker, Trained Cheetah, Traitorous Blood, Tree of Redemption, Tree
of Tales, Treetop Village, Trespasser il-Vec, Tribute to Hunger, Turn to Frog,
Twilight Drover, Typhoid Rats, Uktabi Efreet, Unbreathing Horde, Unruly Mob,
Unstoppable Ash, Urborg Skeleton, Urgent Exorcism, Vampire Interloper,
Vampiric Fury, Vault of Whispers, Vedalken Certarch, Vedalken Ghoul, Venom,
Venomous Dragonfly, Viashino Weaponsmith, Victim of Night, Village
Bell-Ringer, Village Cannibals, Vital Splicer, Voiceless Spirit, Walking
Corpse, Wall of Diffusion, Wall of Tears, Wanderbrine Rootcutters, Wanderwine
Prophets, Waning Wurm, Whispers of the Muse, Wing Splicer, Wirewood Lodge,
Witherscale Wurm, Wolverine Pack, Wooden Stake, Woodland Cemetery, Woodland
Sleuth, Woodripper, Wreath of Geists, Wren's Run Packmaster, Yavimaya Ants,
Yavimaya Hollow, Zhalfirin Commander, Zhalfirin Knight, 

Release 1.18 (September 23, 2011)
============
brought to you by beholder, melvin, and wait321

- added deck editor
- revamped the implementation of continuous effects to be more modular, the
  engine now supports layers and timestamps
- updated the look and feel of the UI and improved the "New Game" screen
- improved the resolution of some token images
- added support for "whenever player gains life" trigger
- added support for non-creature auras
- added support for auras and equipment to have abilities
- ensured that order of blockers does not change when the attacker they were
  blocking is destroyed
- added option for some "may" choices to have a default value
- improved crash handling to handle unhandled exception from any part of the
  program
- improved how AI plays cards it doesn't have to pay the mana cost for
- added option in preferences to select type of card highlighting

- fixed a bug where the "When Targeted" trigger does not activate for spells
  with kicker
- fixed a bug where Noble Hierarch could not be tapped for mana because of a
  typo in the name of the class that implements the mana abilities
- fixed a bug where the program would crash when opening the log book
  immediately after resetting the game
- fixed a bug where the game would crash when you load a deck that contains
  an unsupported card, instead a message indicating the unsupported cards is
  shown
- fixed a bug where resources were not released properly after a sound effect
  is played

- added a total of 280 cards!
  Adventuring Gear
  AEther Figment
  Afflict
  Afterlife
  Ageless Entity
  Aggressive Urge
  Ajani's Mantra
  Ajani's Pridemate
  Akoum Boulderfoot
  Alabaster Mage
  Ancient Hellkite
  Angelfire Crusader
  Angelheart Vial
  Angelic Blessing
  Angelic Chorus
  Angelic Destiny
  Angel of Mercy
  Angel's Feather
  Arc Runner
  Armament Master
  Ascendant Evincar
  Ashenmoor Cohort
  Ashenmoor Liege
  Aura Shards
  Aven Cloudchaser
  Aven Fisher
  Aven Flock
  Azure Mage
  Bad Moon
  Ballista Squad
  Ball Lightning
  Ballynock Cohort
  Balshan Collaborator
  Bandage
  Beastmaster Ascension
  Blade of the Bloodchief
  Blight Sickle
  Blinding Mage
  Blistering Dieflyn
  Bloodthrone Vampire
  Blowfly Infestation
  Bogardan Firefiend
  Bone Saw
  Boomerang
  Bountiful Harvest
  Bramble Creeper
  Briarberry Cohort
  Brink of Disaster
  Burn the Impure
  Burst of Speed
  Carven Caryatid
  Cavern Thoctar
  Char-Rumbler
  Circle of Flame
  Combust
  Condemn
  Corrosive Mentor
  Counsel of the Soratami
  Crabapple Cohort
  Cradle of Vitality
  Crafty Pathmage
  Creeping Mold
  Crescendo of War
  Crimson Mage
  Crucible of Fire
  Crusade
  Damnation
  Darksteel Axe
  Darksteel Plate
  Deathrender
  Demolish
  Demon's Horn
  Devout Lightcaster
  Dragon Arch
  Dragon's Claw
  Dramatic Entrance
  Drudge Skeletons
  Earth Servant
  Edric, Spymaster of Trest
  Elvish Champion
  Elvish Pioneer
  Elvish Piper
  Elvish Visionary
  Essence Drain
  Eternal Witness
  Exclude
  Fallowsage
  Fervor
  Festering Goblin
  Field Marshal
  Fledgling Dragon
  Fleeting Image
  Galepowder Mage
  Gatekeeper of Malakir
  Glaze Fiend
  Gluttonous Slime
  Goblin Balloon Brigade
  Goldenglow Moth
  Gorger Wurm
  Guard Duty
  Hand of the Praetors
  Highway Robber
  Holy Day
  Holy Strength
  Honor Guard
  Hornet Sting
  Horseshoe Crab
  Howling Mine
  Icatian Priest
  Icy Manipulator
  Indestructibility
  Indrik Stomphowler
  Infantry Veteran
  Inspired Charge
  Jace's Ingenuity
  Jade Mage
  Jenara, Asura of War
  Joraga Warcaller
  Judge of Currents
  Kamahl, Pit Fighter
  Karn's Touch
  Kavu Climber
  Kavu Predator
  Kird Ape
  Knighthood
  Kor Aeronaut
  Kor Duelist
  Kor Sanctifiers
  Kor Spiritdancer
  Kraken's Eye
  Lhurgoyf
  Lifelink
  Loam Lion
  Looming Shade
  Lord of the Undead
  Loxodon Mystic
  Loyal Sentry
  Mantis Engine
  Marrow Chomper
  Master of Etherium
  Megrim
  Memory Lapse
  Merfolk Looter
  Merfolk Sovereign
  Mobilization
  Molimo, Maro-Sorcerer
  Molten Rain
  Mortivore
  Mudbrawler Cohort
  Nantuko Husk
  Natural Spring
  Nature's Spiral
  Necropouncer
  Nekrataal
  Neurok Hoversail
  Nightmare
  Night's Whisper
  No-Dachi
  Onyx Mage
  Orcish Artillery
  Peregrine Mask
  Plague Wind
  Plated Geopede
  Primal Rage
  Primordial Hydra
  Prodigal Pyromancer
  Pulse of the Fields
  Pulse of the Forge
  Quest for Renewal
  Quicksilver Amulet
  Quirion Dryad
  Rage Reflection
  Rain of Tears
  Rapacious One
  Ravenous Rats
  Recollect
  Relentless Rats
  Remove Soul
  Reprisal
  Reverberate
  Reviving Dose
  Reya Dawnbringer
  Rhox Pikemaster
  Riddlekeeper
  Righteous Cause
  Righteousness
  Rise from the Grave
  Rites of Flourishing
  Robe of Mirrors
  Roc Egg
  Rock Badger
  Rootwalla
  Rotting Legion
  Roughshod Mentor
  Royal Assassin
  Ruhan of the Fomori
  Rusted Sentinel
  Samite Healer
  Sanguine Bond
  Scepter of Dominance
  Scion of Oona
  Scion of the Wild
  Scroll Thief
  Scute Mob
  Searing Meditation
  Seedborn Muse
  Serra Ascendant
  Serra's Blessing
  Shattered Angel
  Shatterstorm
  Shivan Hellkite
  Shock
  Skinshifter
  Skinwing
  Skullmulcher
  Skyhunter Patrol
  Skyhunter Prowler
  Skyhunter Skirmisher
  Skyshroud Ranger
  Skywinder Drake
  Slaughter Cry
  Smash
  Sorin's Thirst
  Sorin's Vengeance
  Soul's Attendant
  Soul Warden
  Spark Elemental
  Sparkmage Apprentice
  Spirit Link
  Spirit Mantle
  Starlight Invoker
  Stillmoon Cavalier
  Stingerfling Spider
  Stitch Together
  Stonybrook Schoolmaster
  Stun
  Sudden Impact
  Surgespanner
  Swiftfoot Boots
  Taste of Blood
  Tempest of Light
  Terror
  Thran Golem
  Threaten
  Tidings
  Timely Reinforcements
  Titanic Growth
  Tormented Soul
  Treasure Hunter
  Triskelion
  Umezawa's Jitte
  Uncontrolled Infestation
  Unholy Strength
  Vampire Aristocrat
  Venerable Monk
  Verdant Force
  Veteran Armorsmith
  Veteran of the Depths
  Veteran Swordsmith
  Viridian Betrayers
  Viridian Shaman
  Vision Skeins
  Visions of Beyond
  Volcanic Dragon
  Volcanic Hammer
  Wall of Faith
  Wall of Omens
  Wall of Torches
  Warlord's Axe
  Warstorm Surge
  Wickerbough Elder
  Wild Nacatl
  Windrider Eel
  Woodfall Primus
  Worship
  Wrath of God
  Wring Flesh
  Wurm's Tooth
  Yavimaya Enchantress
  Zombie Infestation

Release 1.17b (August 27, 2011)
=============
This is a bug fix release.

- added the following cards:
  Dark Favor
  Deathmark
  Demystify
  Devouring Swarm
  Disentomb
  Raise Dead
  Divine Favor
  Drifting Shade
  Dungrove Elder
  Fiery Hellhound
  Flight
  Fog
  Angel's Mercy
  Gideon's Avenger
  Gladecover Scout
  Goblin Arsonist
  Goblin Fireslinger
  Goblin Tunneler
  Gravedigger
  Greatsword
  Griffin Rider
  Guardians' Pledge
  Hideous Visage
  Kite Shield
  Lava Axe
  Lord of the Unreal
  Manalith
  Manic Vandal
  Mesa Enchantress
  Mind Rot
  Peregrine Griffin
  Pride Guardian
  Reclaim
  (978 cards in total)

- added a crash log to the game, it is stored in the Magarena data folder
  along with the game.cfg and tournament.cfg files. 

- fixed an issue where the game would crash when trying to play sound effects
- fixed bug with Sun Titan's ability to return, previously could return
  spell cards 

Release 1.17 (August 26, 2011)
============
brought to you by beholder and melvin

- added Modern cube (855 cards)
- added the following cards:
  Act of Treason
  AEther Adept (Æther Adept)
  Alluring Siren
  Amphin Cutthroat
  Arbalest Elite
  Armored Warhorse
  Auramancer
  Aven Fleetwing
  Belltower Sphinx
  Benalish Veteran
  Blood Seeker
  Bonebreaker Giant
  Brindle Boar
  Call to the Grave
  Cancel
  Cemetery Reaper
  Chandra's Outrage
  Chasm Drake
  Death Baron
  Frost Titan
  Gideon's Lawkeeper
  Honor of the Pure
  Kederekt Parasite
  Liliana's Caress
  Sangromancer
  Spiteful Visions
  Underworld Dreams
  (945 cards in total)

- changed some game messages to use the player's name instead of "you"
- added option for themes to choose whether to use colored border or overlay to highlight usable cards
  (all themes use colored overlay to highlight cards, except the standard felt theme)
- delay for items on the stack to resolve (used when ...) is now configurable
  from the Preferences menu
- added an About menu
- Mac and Linux launch scripts (Magarena.command and Magarena.sh) now works
  when invoked from any location, previously they assume that Magarena.exe is
  on the path.
- added text search to Card explorer
- enabled AI to make use of abilities that can be activated multiple times
  more effectively

- fixed image for Raging Kavu, was using the Latin version
  (select Arena -> Download images to download the correct card image for
   Raging Kavu and other missing card images)
- fixed wording of Lightning Helix's effect, it refers to Lightning
  Bolt instead of Lightning Helix
- fixed problem with shuffle into library effect in the situation where the
  new card to be shuffled in ends up at the top of the library again
- fixed problem where card image may be missing due to issue with image caching
- fixed problem where duels with more than 40 cards are not loaded correctly

Release 1.16b (July 27, 2011)
=============
This is a bug fix release.

- deck strength viewer is back due to popular demand, moved PLAY button next
  to NEW button to make space for it on netbook resolution
- fixed color of normal selection to green and combat selection to red
- fixed a GUI crash bug
- fixed a bug where the game logic thread would get stuck on non-Linux systems
- implemented a better way to increase responsiveness of the GUI by decreasing
  the priority of the game logic thread

Release 1.16 (July 25, 2011)
============
- 918 cards in total
- added ubeefx cube (617 cards)

- added Mutavault
- added Sun Titan

- fixed: unresponsive GUI, especially when playing against monte carlo AI
- fixed: several crash bugs in monte carlo AI (crash free in more than 100,000 self play games)
- fixed issue 31: previous blocking choices are not cleared if player redo the blocking phase
- fixed issue 28: AI getting stuck when there are many creatures on the battlefield,
  needs more testing

- added variant of minimax that cheats 
- popup card info no longer disappears when the phase changes
- do not auto pass priority when AI blocks your attackers
- increased delay when auto passing priority with item on stack to 2s
- selectable cards are now highlighted with a colored border instead of an overlay
- removed GUI deck strength viewer as it could take a long time to run
- added four more premade decks 
    decks/DL_Burn.dec
    decks/Grundomu_Death_and_Rebirth.dec 
    decks/Grundomu_Knights_Everywhere.dec 
    decks/Kuno_RUw_Aggro_Control.dec

Release 1.15 (June 20, 2011)
============
- 916 cards in total
- added standard cube (303 cards)
- added extended cube (557 cards)
- added legacy cube (915 cards, all except Skullclamp)

- added Tectonic Edge
- added Flashfreeze
- added Pyroclasm
- added Tumble Magnet
- added Inkmoth Nexus
- added Spell Pierce
- added Sphere of the Suns
- added Celestial Purge
- added Mox Opal
- added Signal Pest
- added Negate
- added Mark of Mutiny
- added Explore
- added Nature's Claim

- reduce the number of passes needed if you have the skip single option
  preference enabled (item on the stack is shown for 1s before resolving)
- changed default height and width of the application window to be 1024x600
- use symbols drawn by Goblin Hero
- fixed Vines of Vastwood so that if you target an opponent's creature, the
  opponent cannot target the creature (previously Vines of Vastwood prevents
  the opponent of the controller of the creature from targeting it)
- fixed implementation of first strike, so that triggers may occur after
  dealing first strike damage and before regular damage is dealt (fixes issue
  11: "Double Strike creature that leaves play after first strike still deals
  regular damage (Dread bug)")
- state-based actions are carried out simultaneously instead of one permanent
  at a time (fixes issue 7: "Problem in interaction between 704.3/704.5g and
  613.4")
- fixed card pic for spirit token
- added cancel button to download images dialog, now it is possible to stop
  downloading
- add this README.txt to the game's help menu
- added caching to Monte Carlo Tree Search AI so that simulations performed
  for one decision can be reused when computing the next decision
- evaluated the AIs with different parameters, results available at
  http://code.google.com/p/magarena/wiki/AIStrength

Release 1.14 (May 28, 2011)
============
- 601 cards in default cube
- 901 cards in all cube (includes those in default)

- added Naya Hushblade (Alara Reborn, Common)

- restored ability to download card images from the WWW
- fixed bug with protection from monocolored and land that become creatures
- fixed bug with "as long as you control another multicolored permanent" and
  land that become creatures
- enhanced error handling by printing error messages to stderr when an exception occurs
- renamed "can't be the target of spells or abilities your opponents control"
  to hexproof
- by default all card images shown at 312x445 for consistency, can be changed
  via option in preference menu to show original image size
- filter legal targets off by default as it is confusing to new users
- no need to restart after downloading images
- added hexproof to list of keywords [1.14a]
- added missing sounds folder [1.14a]

Release LE 1.13 (April 24, 2011)
===============

- default cube (600 cards)
- all cube (900 cards)

- added sound effects, disabled by default, can be enabled in preferences
- right mouse click on hand zone can now be used as a shortcut for action button
- infect and wither abilities are also shown on cards with same icon as deathtouch

Release LE 1.12 (April 17, 2011)
===============

- default cube (567 cards)
- all cube (888 cards)

- modified game to work with a premade Magarena data folder
- added selectable avatar sets in preferences, separate from theme
- added unlimited undo support 
- added "Reset game" in menu, undoing all moves
- added M key as an additional shortcut for messages

Release 1.11 (April 11, 2011)
============

- default cube (555 cards)
- all cube (876 cards)

- renamed the two standard cubes to default and all
- improved displaying of messages with scroll bar and toggle button + F1 shortcut to show or hide messages
- improved mana cost images (if already installed, delete symbols folder in Magarena data folder and load images)
- improved card definition files, they now also contain the image URL and cube information
- improved scoring for unnecessary equipping
- three new AI implementations are available next to the default minimax AI : Monte Carlo, Vegas and Random
- the preferred AI can be selected in preferences, the deck strength calculator always uses minimax AI
- added New and Load Duel buttons on startup screen
- added option to select a random deck from decks folder in New Duel dialog (folder icon)

- fixed Lightning Helix, it can now target your own permanents with filter legal targets enabled

Release 1.10 (April 1, 2011)
============

- ubeefx cube (544 cards)
- singularita cube (865 cards)

- redesigned card image loading (low quality images are no longer needed)
- high quality random card at startup if enabled in settings

Release 1.9 (March 22, 2011)
===========

- ubeefx cube (542 cards)
- singularita cube (862 cards)

- if a folder Magarena is present in the same folder as where Magarena is started, that is used as the Magarena data folder
- added extra settings to themes (see customizing Wiki page)
- added support for infect keyword and poison counters

- fixed Solemn Offering, it can now target your own permanents with filter legal targets enabled
- fixed handling of invalid avatars

Release 1.8 (March 13, 2011)
===========

- ubeefx cube (530 cards)
- singularita cube (850 cards)

- improved deck generator
- added option to play with monocolored generated decks
- changed function of Life + slider in difficulty settings so that it gives extra life to computer for added difficulty

- fixed must attack if able when power is zero or lower
- fixed apostrophes in Keywords screen

Release 1.7 (January 14, 2011)
===========

- 8 new cards (512 total)

- new splash screen and small UI tweaks
- added mana info for lands in hand and on battlefield
- added Enter as hotkey to switch between image and text mode
- added support for user made UI themes that can be downloaded separately
- added support for user defined card cubes next to the default cube

- fixed Pongify, it can now target your own permanents with filter legal targets enabled

Release 1.6 (January 2, 2011)
===========

- 4 new cards (504 total)

- mana cost, power & toughness and some abilities on cards in hand are now displayed in graphical view
- card drawing is now optional for Snake Umbra and Mask of Memory
- added support for intimidate and battle cry keywords

- fixed how deathtouch was handled causing a number of issues
- fixed endless loop in custom card shuffler for decks with a lot of CMC > 4 cards
- fixed Into the Roil, it can now target your own permanents with filter legal targets enabled

Release 1.5 (December 28, 2010)
===========

- 5 new cards (500 total)

- added random card to the startup screen

Release 1.4 (December 26, 2010)
===========

- 5 new cards (495 total)

- added light wood skin
- improved display of valid choices
- improved AI destroy and exile target pickers

- fixed Omnibian ability on level up creatures

Release 1.3 (December 24, 2010)
===========
 
- 15 new cards (490 total)
- replaced Elite Vanguard with Steppe Lynx
- replaced Curse of Chains with Narcolepsy

- added Magarena icon to application frame
- added keyboard shortcuts for action button : space or right key
- added keyboard shortcuts for undo button : escape or left key
- increased maximum difficulty level to 8 (default is 6)
- configurable number of games and difficulty level for deck strength calculator
- configurable card image popup delay in preferences (0 - 500 ms)
- configurable high quality transparent card image popups (turned off by default)
- improved display of stack in graphical view
- zoom effect on photo gadget is now delayed
- added support for sub folders in images data folder

- fixed triggered ability of Archon of Justice when discarding

Release 1.2 (December 18, 2010)
===========

- 25 new cards (475 total)
- replaced Disperse with Into the Roil 
- replaced Gruul War Plow with Chimeric Mass

- support for loading and saving decks to an editable text format in decks folder
- disabled deck editing when duel is in progress
- added two and three color wild cards to new duel dialog
- charge counters are now displayed with amount in graphical view
- added tooltips for small icon buttons

- fixed aura or equipment that stays attached when creature has protection
- fixed sacrifice or exile at and end of turn not always triggering (Rakdos Guildmage)
- fixed self targeting of spells on stack (counters)
- fixed wrong multiple target behavior (Char and Goblin Artillery)
- fixed converted mana cost for X spells on stack (Draining Whelk)
- fixed triggered ability for multiple activations of Raging Ravine

Release 1.1 (December 7, 2010)
===========

- new splash screen
- display of version information on startup
- improved download images dialog with proxy support
- support for two color decks
- support for replacing cards in decks with edit button that shows the card explorer
- added Unhinged basic lands as alternate images for basic lands
- icons for summoning sickness, flying, first & double strike, trample and deathtouch in graphical view
- viewers for exile zone

- fixed summoning sickness rule
- fixed Spider Umbra aura

Release 1.0 (November 25, 2010)
===========

- initial release (450 cards)
