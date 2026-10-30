package com.example.data.local

import com.example.data.model.*

object PreloadedData {

    val CROPS = listOf(
        Crop(
            id = "teff",
            nameEn = "Teff",
            nameAm = "ጤፍ",
            scientificName = "Eragrostis tef",
            category = CropCategory.CEREAL,
            iconEmoji = "🌾",
            descriptionEn = "Ethiopia's indigenous staple cereal grain, gluten-free, resilient to diverse altitudes.",
            descriptionAm = "የኢትዮጵያ ሀገር በቀል ዋና የእህል ሰብል፤ በሁሉም ከፍታዎች ላይ የሚበቅል ጠቃሚ እህል።",
            keyRegions = listOf("Amhara (East Gojjam)", "Oromia (Ada'a / East Shewa)", "Tigray"),
            mainSeason = "Meher (June - November)",
            commonDiseasesCount = 3
        ),
        Crop(
            id = "maize",
            nameEn = "Maize (Corn)",
            nameAm = "በቆሎ",
            scientificName = "Zea mays",
            category = CropCategory.CEREAL,
            iconEmoji = "🌽",
            descriptionEn = "Major caloric cereal grown widely in mid-altitude and lowland farming zones.",
            descriptionAm = "በመካከለኛና ዝቅተኛ ቆላማ አካባቢዎች በስፋት የሚመረት ከፍተኛ ምርት የሚሰጥ እህል።",
            keyRegions = listOf("Oromia (Jimma, Buno Bedele)", "SNNPR", "Amhara (West Gojjam)", "Benishangul"),
            mainSeason = "Meher & Belg",
            commonDiseasesCount = 4
        ),
        Crop(
            id = "wheat",
            nameEn = "Wheat",
            nameAm = "ስንዴ",
            scientificName = "Triticum aestivum",
            category = CropCategory.CEREAL,
            iconEmoji = "🌾",
            descriptionEn = "Critical highland food security crop, intensively cultivated in Arsi-Bale wheat belts.",
            descriptionAm = "በደጋማ የአርሲ እና ባሌ ቆላማ መስኖ አካባቢዎች በስፋት የሚመረት ስትራቴጂክ ሰብል።",
            keyRegions = listOf("Oromia (Arsi, Bale)", "Amhara (North & South Gondar)", "Tigray"),
            mainSeason = "Meher & Irrigated Winter",
            commonDiseasesCount = 4
        ),
        Crop(
            id = "coffee",
            nameEn = "Coffee Arabica",
            nameAm = "የኢትዮጵያ ቡና",
            scientificName = "Coffea arabica",
            category = CropCategory.CASH_CROP,
            iconEmoji = "☕",
            descriptionEn = "Ethiopia's primary export crop and cultural heritage, grown in highland forest shade.",
            descriptionAm = "የኢትዮጵያ ትልቁ የውጭ ምንዛሬ ምንጭ፤ በደን ጥላ ስር የሚመረት ቅርስ።",
            keyRegions = listOf("Oromia (Jimma, Keffa, Guji, Harar)", "Sidama", "South West Ethiopia"),
            mainSeason = "Perennial (Harvest Nov - Jan)",
            commonDiseasesCount = 3
        ),
        Crop(
            id = "enset",
            nameEn = "Enset (False Banana)",
            nameAm = "እንሰት",
            scientificName = "Ensete ventricosum",
            category = CropCategory.ROOT_TUBER,
            iconEmoji = "🌴",
            descriptionEn = "The tree against hunger, staple for over 20 million people in southern and southwestern Ethiopia.",
            descriptionAm = "የረሃብ መከላከያ፤ በደቡብና ደቡብ ምዕራብ ኢትዮጵያ የህዝብ መተዳደሪያ ዋና ሰብል።",
            keyRegions = listOf("Sidama", "Central Ethiopia (Gurage, Hadiya)", "South Ethiopia (Gamo, Wolaita)"),
            mainSeason = "Perennial (Matures in 4-6 yrs)",
            commonDiseasesCount = 2
        ),
        Crop(
            id = "tomato",
            nameEn = "Tomato",
            nameAm = "ቲማቲም",
            scientificName = "Solanum lycopersicum",
            category = CropCategory.VEGETABLE,
            iconEmoji = "🍅",
            descriptionEn = "High-value commercial horticultural vegetable prone to foliar blights in humid weather.",
            descriptionAm = "በመስኖ እና በዝናብ ወቅት በስፋት የሚመረት የገበያ አትክልት።",
            keyRegions = listOf("Rift Valley (Meki, Zway)", "Oromia", "Amhara"),
            mainSeason = "Year-round (Irrigated & Rainfed)",
            commonDiseasesCount = 3
        ),
        Crop(
            id = "potato",
            nameEn = "Potato",
            nameAm = "ድንች",
            scientificName = "Solanum tuberosum",
            category = CropCategory.ROOT_TUBER,
            iconEmoji = "🥔",
            descriptionEn = "Essential highland tuber crop, sensitive to Late Blight during wet seasons.",
            descriptionAm = "በደጋማ ቦታዎች የሚመረት ዋና ምግብና ገቢ አስገኝ ስራስር።",
            keyRegions = listOf("Amhara (Awi Zone)", "Oromia (West Shewa)", "Sidama"),
            mainSeason = "Belg & Meher",
            commonDiseasesCount = 2
        ),
        Crop(
            id = "faba_bean",
            nameEn = "Faba Bean",
            nameAm = "የባቄላ ሰብል",
            scientificName = "Vicia faba",
            category = CropCategory.LEGUME,
            iconEmoji = "🫘",
            descriptionEn = "Nitrogen-fixing highland pulse vital for soil regeneration and protein nutrition.",
            descriptionAm = "የአፈር ለምነትን የሚጨምርና ለምግብነት የሚውል ደጋማ የጥራጥሬ ሰብል።",
            keyRegions = listOf("Amhara (Gojjam, Wollo)", "Oromia (Bale, Shewa)", "Tigray"),
            mainSeason = "Meher",
            commonDiseasesCount = 2
        ),
        Crop(
            id = "chickpea",
            nameEn = "Chickpea",
            nameAm = "ሽምብራ",
            scientificName = "Cicer arietinum",
            category = CropCategory.LEGUME,
            iconEmoji = "🫘",
            descriptionEn = "Residual moisture pulse grown in vertisol clay plains after main cereal harvest.",
            descriptionAm = "በከባድ የኮትቻ አፈር ላይ በመስከረም ወር በቅሪት እርጥበት የሚዘራ የገበያ ሰብል።",
            keyRegions = listOf("Amhara (North Shewa, Dembia)", "Oromia (Ada'a, Gimbichu)"),
            mainSeason = "Late Meher (Sept - Jan)",
            commonDiseasesCount = 2
        ),
        Crop(
            id = "sorghum",
            nameEn = "Sorghum",
            nameAm = "ማሽላ",
            scientificName = "Sorghum bicolor",
            category = CropCategory.CEREAL,
            iconEmoji = "🌾",
            descriptionEn = "Drought-hardy cereal flourishing in dry mid-to-lowland agro-ecologies.",
            descriptionAm = "ድርቅን የሚቋቋም በቆላማና ወይናደጋ አካባቢዎች የሚመረት እህል።",
            keyRegions = listOf("Oromia (East/West Hararghe)", "Amhara (North Wollo)", "Tigray"),
            mainSeason = "Meher",
            commonDiseasesCount = 2
        )
    )

    val DISEASES = listOf(
        // MAIZE DISEASES
        Disease(
            id = "maize_blight",
            cropId = "maize",
            nameEn = "Northern Corn Leaf Blight",
            nameAm = "የበቆሎ ቅጠል ማቃጠል በሽታ",
            scientificName = "Exserohilum turcicum",
            pathogenType = PathogenType.FUNGAL,
            affectedOrgans = listOf(PlantOrgan.LEAF),
            symptomsEn = listOf(
                "Long, elliptical, cigar-shaped grayish-green to tan lesions on lower leaves first",
                "Lesions measure 2.5 to 15 cm long with distinct dark margins",
                "Severe blighting causes premature death of canopy, reducing grain fill by up to 50%"
            ),
            symptomsAm = listOf(
                "በታችኛው የበቆሎ ቅጠሎች ላይ የሲጋራ ቅርጽ ያላቸው ግራጫማ ወይም ቡናማ ነጠብጣቦች መታየት",
                "ቁስሎቹ ከ2 እስከ 15 ሳ.ሜ ርዝመት ኖሯቸው ወደ ሙሉ ቅጠል ይዛመታሉ",
                "ቅጠሉን በማድረቅ የምርት መጠኑን በግማሽ ይቀንሳል"
            ),
            favorableConditions = "Moderate temperatures (18°C–27°C) with prolonged leaf wetness (dew or rain)",
            organicTreatmentsEn = listOf(
                "Rotate with non-host crops like Faba bean, Teff or Chickpea for at least 2 seasons",
                "Deep tillage post-harvest to bury infected crop residues under 15 cm of soil",
                "Plant certified resistant varieties released by Bako Agricultural Research Center (e.g., BH-661, BH-546)"
            ),
            organicTreatmentsAm = listOf(
                "ማሳውን በሌሎች ሰብሎች (ባቄላ፣ ጤፍ) ማፈራረቅ",
                "ከተሰበሰበ በኋላ የቀረውን ገለባ በጥልቅ ማረስና ማቃጠል ወይም ማዳበሪያ ማድረግ",
                "በባኮ ግብርና ምርምር ማዕከል የተመሰከረላቸውን በሽታ ተቋቋሚ ዝርያዎችን (BH-661) መጠቀም"
            ),
            chemicalTreatmentsEn = listOf(
                "Foliar spray with Mancozeb 80% WP at 2.5 kg/ha upon first appearance of lesions",
                "Azoxystrobin + Difenoconazole (Amistar Top) at 500 ml/ha if spread reaches ear leaf before tasseling"
            ),
            chemicalTreatmentsAm = listOf(
                "የማንኮዜብ (Mancozeb 80% WP) ፀረ-ፈንገስ 2.5 ኪሎ በሄክታር በመጀመሪያ ምልክት ላይ መርጨት",
                "አሚስታር ቶፕ (Amistar Top) 500 ሚሊ በሄክታር ወደ ላይኛው ቅጠል ከመዛመቱ በፊት መጠቀም"
            ),
            preventionMethodsEn = listOf(
                "Avoid excessive planting density to improve airflow through canopy",
                "Ensure balanced soil potassium to reinforce plant cell wall resistance"
            ),
            preventionMethodsAm = listOf(
                "የተክሎችን እርቀት መጠበቅ (75 ሳ.ሜ በረድፍ መካከል እና 25 ሳ.ሜ በተክል መካከል)",
                "የተመጣጠነ ማዳበሪያ (NPSB) በወቅቱ መጠቀም"
            ),
            severityTypical = DiseaseSeverity.MODERATE
        ),
        Disease(
            id = "maize_fall_armyworm",
            cropId = "maize",
            nameEn = "Fall Armyworm Damage",
            nameAm = "የመኸር ሰራዊት አባጨጓሬ ጉዳት",
            scientificName = "Spodoptera frugiperda",
            pathogenType = PathogenType.PEST,
            affectedOrgans = listOf(PlantOrgan.LEAF, PlantOrgan.STEM, PlantOrgan.FRUIT_HEAD),
            symptomsEn = listOf(
                "Window-pane feeding marks on young leaves transitioning to ragged holes",
                "Frass (sawdust-like insect excrement) packed inside the central leaf whorl",
                "Inverted 'Y' suture visible on caterpillar's dark head capsule"
            ),
            symptomsAm = listOf(
                "በለጋ የበቆሎ ቅጠሎች ላይ መስኮት የሚመስሉ ቀዳዳዎች መታየት",
                "በእምቡጥ ውስጥ እንደ እንጨት ፍቅፋቂ ያለ የአባጨጓሬ ዓይነ-ምድር መከማቸት",
                "በአባጨጓሬው ራስ ላይ የተገለበጠ 'Y' ምልክት መታየት"
            ),
            favorableConditions = "Warm dry spells followed by intermittent rainfall; active in early vegetative stages",
            organicTreatmentsEn = listOf(
                "Hand-pick egg masses and caterpillars from whorls in early mornings",
                "Drop sand or wood ash mixed with chili powder into infested central whorls",
                "Spray neem seed kernel extract (NSKE 5%) or biopesticides (Bacillus thuringiensis)"
            ),
            organicTreatmentsAm = listOf(
                "ጠዋት ጠዋት በእጅ ለቅሞ ማጥፋት",
                "ጥሩ አመድ ወይም አሸዋ ከበርበሬ ዱቄት ጋር ቀላቅሎ ወደ እምቡጡ ማፍሰስ",
                "የኒም (Neem) ዛፍ ቅጠል ውሃ በመርጨት መከላከል"
            ),
            chemicalTreatmentsEn = listOf(
                "Apply Ampligo 150 ZC (Chlorantraniliprole + Lambda-cyhalothrin) at 200–300 ml/ha directly directed into whorls",
                "Spinetoram (Radiant 120 SC) at recommended extension dose; rotate chemical classes to prevent resistance"
            ),
            chemicalTreatmentsAm = listOf(
                "አምፕሊጎ (Ampligo 150 ZC) 200-300 ሚሊ በሄክታር በቀጥታ ወደ በቆሎው እምቡጥ መርጨት",
                "የኬሚካል መርጫዎችን በማፈራረቅ ተባዩ እንዳይላመድ ማድረግ"
            ),
            preventionMethodsEn = listOf(
                "Early planting at onset of Belg or Meher rains to escape peak moth migration",
                "Intercrop with Desmodium (push-pull technology) where practiced"
            ),
            preventionMethodsAm = listOf(
                "ዝናብ እንደጀመረ ቀድሞ መዝራት",
                "ከሌሎች ሰብሎች ጋር ደባልቆ መዝራት"
            ),
            severityTypical = DiseaseSeverity.SEVERE
        ),

        // WHEAT DISEASES
        Disease(
            id = "wheat_stem_rust",
            cropId = "wheat",
            nameEn = "Wheat Stem Rust (Ug99 Alert)",
            nameAm = "የስንዴ ግንድ ዋግ (Ug99)",
            scientificName = "Puccinia graminis f. sp. tritici",
            pathogenType = PathogenType.FUNGAL,
            affectedOrgans = listOf(PlantOrgan.STEM, PlantOrgan.LEAF),
            symptomsEn = listOf(
                "Reddish-brown, elongated, raised pustules (uredinia) on stems and leaf sheaths",
                "Torn epidermal tissue surrounding pustules, revealing powdery brick-red spores",
                "Severe lodge of stems and complete shriveling of wheat grains"
            ),
            symptomsAm = listOf(
                "በስንዴው ግንድ እና በቅጠሉ አገዳ ላይ የጡብ ቀለም ያላቸው የፈንገስ እብጠቶች መታየት",
                "የግንዱ ቆዳ ተላቆ የቡናማ ዱቄት ብናኝ መበተን",
                "ስንዴው ተሰባብሮ መውደቅና እህሉ ቦዶ ሆኖ መቅረት"
            ),
            favorableConditions = "Warm humid days (18°C–30°C) with nighttime dew in Ethiopian highland valleys (Bale/Arsi)",
            organicTreatmentsEn = listOf(
                "Immediately eradicate wild barberry and alternative host grasses around field borders",
                "Sow certified rust-resistant Ethiopian wheat varieties (e.g., Kakaba, Danda'a, Kingbird, Utuba)"
            ),
            organicTreatmentsAm = listOf(
                "በማሳው ዙሪያ ያሉ የዱር ቆሻሻ ሳሮችን ማስወገድ",
                "የዋግ በሽታን የሚቋቋሙ የተመሰከረላቸው የስንዴ ዝርያዎችን (ካካባ፣ ዳንዳአ፣ ኡቱባ) መዝራት"
            ),
            chemicalTreatmentsEn = listOf(
                "Fungicide emergency spray: Tebuconazole (Folicur 250 EC) at 500 ml/ha at first pustule detection",
                "Propiconazole (Tilt 250 EC) at 500 ml/ha; second application 14 days later if weather remains wet"
            ),
            chemicalTreatmentsAm = listOf(
                "ቴቡኮናዞል (Folicur 250 EC) 500 ሚሊ በሄክታር የመጀመሪያው ምልክት እንደታየ በፍጥነት መርጨት",
                "ዝናብ ከቀጠለ ከ14 ቀናት በኋላ በድጋሚ መርጨት"
            ),
            preventionMethodsEn = listOf(
                "Strict regional surveillance with local Woreda Development Agents (DAs)",
                "Do not re-use grain from heavily rusted fields as planting seed"
            ),
            preventionMethodsAm = listOf(
                "ከአካባቢው የልማት ጣቢያ ባለሙያ (DA) ጋር በቅርበት መከታተል",
                "በሽታው ያጠቃውን ስንዴ ለቀጣይ ዘር አለመጠቀም"
            ),
            severityTypical = DiseaseSeverity.SEVERE
        ),

        // TEFF DISEASES
        Disease(
            id = "teff_rust",
            cropId = "teff",
            nameEn = "Teff Rust",
            nameAm = "የጤፍ ዋግ በሽታ",
            scientificName = "Uromyces eragrostidis",
            pathogenType = PathogenType.FUNGAL,
            affectedOrgans = listOf(PlantOrgan.LEAF, PlantOrgan.STEM),
            symptomsEn = listOf(
                "Small, circular to oblong orange-brown powdery pustules scattered on both leaf surfaces",
                "Chlorotic halos surrounding pustules, turning leaves prematurely yellow and dry",
                "Reduced tiller vigor and poor panicle seed set"
            ),
            symptomsAm = listOf(
                "በጤፍ ቅጠል ሁለቱም ገጽ ላይ ትናንሽ ብርቱካናማ-ቡናማ የዱቄት ነጠብጣቦች መታየት",
                "ቅጠሉ ወደ ቢጫነት ተቀይሮ ቀድሞ መድረቅ",
                "የጤፉ ዛላ እንዳይወጣና ምርቱ እንዲቀንስ ማድረጉ"
            ),
            favorableConditions = "High relative humidity (>85%) during flowering and early grain-filling in Ada'a / Gojjam",
            organicTreatmentsEn = listOf(
                "Plant certified improved varieties like Quncho (DZ-Cr-37) or Boset with proper seed dressing",
                "Avoid over-fertilizing with excessive Nitrogen, which fosters lush susceptible canopy growth"
            ),
            organicTreatmentsAm = listOf(
                "የተመረጡ የጤፍ ዝርያዎችን (ቁንጮ DZ-Cr-37 ወይም ቦሴት) በንጹህ ዘር መዝራት",
                "የዩሪያ ማዳበሪያን ከመጠን በላይ አለመጠቀም"
            ),
            chemicalTreatmentsEn = listOf(
                "Spray Triadimefon (Bayleton 25 WP) at 500 g/ha or Mancozeb at early heading if rust incidence exceeds 10%"
            ),
            chemicalTreatmentsAm = listOf(
                "ቤይሌተን (Bayleton 25 WP) 500 ግራም በሄክታር በለጋ ወቅት መርጨት"
            ),
            preventionMethodsEn = listOf(
                "Row planting with 20 cm spacing to improve aeration instead of excessive broadcasting",
                "Ensure proper field drainage to avoid water stagnation"
            ),
            preventionMethodsAm = listOf(
                "በመስመር መዝራት (20 ሳ.ሜ ርቀት) አየር እንዲዘዋወር ያደርጋል",
                "የማሳውን የውሃ መፋሰሻ ማስተካከል"
            ),
            severityTypical = DiseaseSeverity.MILD
        ),

        // COFFEE ARABICA DISEASES
        Disease(
            id = "coffee_leaf_rust",
            cropId = "coffee",
            nameEn = "Coffee Leaf Rust (CLR)",
            nameAm = "የቡና ቅጠል ዝገት (ዋግ)",
            scientificName = "Hemileia vastatrix",
            pathogenType = PathogenType.FUNGAL,
            affectedOrgans = listOf(PlantOrgan.LEAF),
            symptomsEn = listOf(
                "Yellowish oily spots on the upper leaf surface",
                "Powdery orange-yellow spore dust (urediniospores) on the underside of corresponding spots",
                "Extensive defoliation leaving bare twigs ('dieback') and severe berry drop"
            ),
            symptomsAm = listOf(
                "በቡና ቅጠል የላይኛው ገጽ ላይ የዘይት መልክ ያላቸው ቢጫ ነጠብጣቦች መታየት",
                "በቅጠሉ የጀርባ ገጽ ላይ ብርቱካናማ የዱቄት ብናኝ መፈጠር",
                "ቅጠሉ በብዛት መርገፍና የቡና ዛፉ ቅርንጫፍ መድረቅ"
            ),
            favorableConditions = "High rainfall, dense unpruned shade, and temperatures between 21°C and 25°C",
            organicTreatmentsEn = listOf(
                "Regular pruning of coffee branches and shade trees to permit 30-40% direct sunlight penetration",
                "Use Ethiopian Jimma Agricultural Research Center (JARC) rust-resistant selections (e.g., 741, 744, 7440, Gawe)",
                "Apply compost and potassium to fortify coffee bush vigor"
            ),
            organicTreatmentsAm = listOf(
                "የቡና ዛፉንና የጥላ ዛፎችን ቅርንጫፍ መግረዝ (ብርሃንና አየር እንዲያገኝ)",
                "በጅማ ግብርና ምርምር ማዕከል የተለቀቁ ተቋቋሚ ዝርያዎችን (741፣ 744፣ 7440) መትከል",
                "የተፈጥሮ ማዳበሪያና ኮምፖስት በመጨመር ዛፉ እንዲጠነክር ማድረግ"
            ),
            chemicalTreatmentsEn = listOf(
                "Preventative application of Copper Hydroxide (Kocide 2000) at 2.5 kg/ha before onset of big rains",
                "Systemic spray with Triadimenol (Bayfidan) or Pyraclostrobin during active rust flush"
            ),
            chemicalTreatmentsAm = listOf(
                "የኮፐር (Copper Hydroxide/Kocide) ፀረ-ፈንገስ ክረምት ከመግባቱ በፊት መርጨት",
                "በሽታው ከተስፋፋ ባይፊዳን (Bayfidan) በመመሪያው መሰረት መጠቀም"
            ),
            preventionMethodsEn = listOf(
                "Maintain optimal shade density (neither zero shade nor suffocating dark shade)",
                "Sanitize pruning tools between infected and healthy coffee blocks"
            ),
            preventionMethodsAm = listOf(
                "ተመጣጣኝ ጥላ መጠበቅ",
                "የመግረዣ መጋዞችንና ቢላዎችን በማጽዳት መጠቀም"
            ),
            severityTypical = DiseaseSeverity.MODERATE
        ),
        Disease(
            id = "coffee_berry_disease",
            cropId = "coffee",
            nameEn = "Coffee Berry Disease (CBD)",
            nameAm = "የቡና ፍሬ ማበስበስ በሽታ (CBD)",
            scientificName = "Colletotrichum kahawae",
            pathogenType = PathogenType.FUNGAL,
            affectedOrgans = listOf(PlantOrgan.FRUIT_HEAD),
            symptomsEn = listOf(
                "Dark, sunken, water-soaked anthracnose lesions on green developing coffee berries",
                "Lesions expand rapidly turning the entire berry black, mummified, and hollow",
                "Heavy premature dropping of infected green cherries causing up to 80% crop loss"
            ),
            symptomsAm = listOf(
                "በለጋ አረንጓዴ የቡና ፍሬዎች ላይ ጥቁር የሰመጠ የበሰበሰ ነጠብጣብ መታየት",
                "ፍሬው ሙሉ በሙሉ ጠቁሮ ደርቆ መርገፍ",
                "እስከ 80 በመቶ የሚደርስ የቡና ምርት ኪሳራ ማድረስ"
            ),
            favorableConditions = "High altitude (>1600m), cool temperatures (15°C–22°C), and misty persistent rains",
            organicTreatmentsEn = listOf(
                "Strip off and burn all mummified black berries remaining on bushes from previous harvest",
                "Plant proven JARC CBD-resistant CBD selections (e.g., 74110, 74112, 754)"
            ),
            organicTreatmentsAm = listOf(
                "በዛፉ ላይ የቀሩትን የደረቁና ያረጁ ጥቁር ፍሬዎችን ለቅሞ ማቃጠል",
                "በሽታውን የሚቋቋሙ የጅማ ዝርያዎችን (74110፣ 74112) መጠቀም"
            ),
            chemicalTreatmentsEn = listOf(
                "Copper Oxychloride 50% WP spray (5 kg/ha) initiated 2-4 weeks after flowering",
                "Dithianon (Delan 700 WDG) applied at 4-week intervals during the main expansion period"
            ),
            chemicalTreatmentsAm = listOf(
                "ኮፐር ኦክሲክሎራይድ (Copper Oxychloride) ቡናው ካበበ ከ2-4 ሳምንት በኋላ መርጨት"
            ),
            preventionMethodsEn = listOf(
                "Inspect higher elevation plots weekly throughout the July-August rains"
            ),
            preventionMethodsAm = listOf(
                "በክረምቱ ወራት በየሳምንቱ ማሳውን መፈተሽ"
            ),
            severityTypical = DiseaseSeverity.SEVERE
        ),

        // ENSET DISEASE
        Disease(
            id = "enset_bacterial_wilt",
            cropId = "enset",
            nameEn = "Enset Bacterial Wilt (BXW)",
            nameAm = "የእንሰት ባክቴሪያ አገርሽ በሽታ",
            scientificName = "Xanthomonas vasicola pv. musacearum",
            pathogenType = PathogenType.BACTERIAL,
            affectedOrgans = listOf(PlantOrgan.WHOLE_PLANT, PlantOrgan.LEAF, PlantOrgan.STEM),
            symptomsEn = listOf(
                "Sudden yellowing and wilting of the innermost young unfurled leaf (heart leaf)",
                "Yellow to brownish bacterial slime oozing when pseudostem is cut horizontally",
                "Total collapse and rotting of the enset corm (underground food base) within weeks"
            ),
            symptomsAm = listOf(
                "የውስጠኛው ለጋ ቅጠል ድንገት ቢጫ ሆኖ መጥወልወልና መውደቅ",
                "የእንሰቱ ግንድ ሲቆረጥ ቢጫና ቡናማ የሚጣበቅ የባክቴሪያ ፈሳሽ መውጣት",
                "የእንሰቱ ጉቶና አምቡላ በሳምንታት ውስጥ መበስበስ"
            ),
            favorableConditions = "Spread via unsterilized machetes, contaminated farm tools, domestic animal browsing, and porcupines",
            organicTreatmentsEn = listOf(
                "Strict quarantine: immediately uproot, chop, and bury or burn infected enset plants in a pit",
                "Flaming or bleaching farm tools (machetes, knives) with 10% sodium hypochlorite after every plant cut",
                "Fence enset plantations to bar stray livestock that transmit bacterial ooze between plants"
            ),
            organicTreatmentsAm = listOf(
                "የታመመውን እንሰት ወዲያውኑ ነቅሎ መቅበር ወይም ማቃጠል",
                "የመቁረጫ ቢላዎችንና ቆንጨራዎችን በእሳት ወይም በበረኪና ማጽዳት",
                "ከብቶች እንዳይገቡ ማሳውን በአጥር መከለል"
            ),
            chemicalTreatmentsEn = listOf(
                "No effective chemical cures exist for bacterial vascular wilt; strict phytosanitary hygiene is mandatory."
            ),
            chemicalTreatmentsAm = listOf(
                "ለዚህ የባክቴሪያ በሽታ መድሃኒት የለም፤ ዋነኛው መፍትሄ የታመመውን ነቅሎ ማጥፋትና ንፅህና መጠበቅ ነው"
            ),
            preventionMethodsEn = listOf(
                "Source disease-free propagation suckers from certified DA nurseries",
                "Coordinated community-wide eradication campaigns across the entire Kebele"
            ),
            preventionMethodsAm = listOf(
                "ጤናማ ችግኞችን ብቻ ከአስተማማኝ ቦታ ማምጣት",
                "በመንደር ደረጃ ከጎረቤቶች ጋር በጋራ መከላከል"
            ),
            severityTypical = DiseaseSeverity.SEVERE
        ),

        // TOMATO DISEASES
        Disease(
            id = "tomato_late_blight",
            cropId = "tomato",
            nameEn = "Tomato Late Blight",
            nameAm = "የቲማቲም የዋግ (ብላይት) በሽታ",
            scientificName = "Phytophthora infestans",
            pathogenType = PathogenType.FUNGAL,
            affectedOrgans = listOf(PlantOrgan.LEAF, PlantOrgan.STEM, PlantOrgan.FRUIT_HEAD),
            symptomsEn = listOf(
                "Water-soaked dark lesions spreading rapidly across leaves with pale green borders",
                "White frosty fungal growth visible on the underside of affected leaves in high humidity",
                "Large, firm, greasy brown lesions on green and ripening tomato fruits"
            ),
            symptomsAm = listOf(
                "በቅጠል ላይ በፍጥነት የሚዛመት የውሃ መልክ ያለው ጥቁር ነጠብጣብ",
                "ከቅጠሉ ጀርባ ነጭ የፈንገስ ሻጋታ መታየት",
                "በቲማቲሙ ፍሬ ላይ የደረቀ ቡናማ የሰመጠ ቁስል መፈጠር"
            ),
            favorableConditions = "Cool nights (10°C–15°C) and warm days (18°C–22°C) with persistent rain or fog",
            organicTreatmentsEn = listOf(
                "Prune lower suckers and train tomatoes on trellises/stakes to prevent splash infection from soil",
                "Drip irrigation or furrow watering to keep canopy dry (never use overhead sprinklers during blight season)"
            ),
            organicTreatmentsAm = listOf(
                "ቲማቲሙን በአጣና ማሰር ቅጠሉ መሬት እንዳይነካ ማድረግ",
                "ውሃ ስር ስሩን ማጠጣት (ቅጠሉ ላይ አለማፍሰስ)"
            ),
            chemicalTreatmentsEn = listOf(
                "Curative spray: Metalaxyl + Mancozeb (Ridomil Gold MZ 68 WG) at 2.5 kg/ha applied within 48h of symptoms",
                "Protectant spray: Chlorothalonil (Daconil 720 SC) at 2.0 L/ha for preventative cover"
            ),
            chemicalTreatmentsAm = listOf(
                "ሪዶሚል ጎልድ (Ridomil Gold) 2.5 ኪሎ በሄክታር ወዲያውኑ መርጨት",
                "ዳኮኒል (Daconil) ለመከላከል መጠቀም"
            ),
            preventionMethodsEn = listOf(
                "Do not rotate with or plant adjacent to Irish potatoes (host same pathogen)",
                "Space plants at least 50 cm apart in raised ridges"
            ),
            preventionMethodsAm = listOf(
                "ከድንች ማሳ አጠገብ አለመትከል (ተመሳሳይ በሽታ ያጋጥማቸዋል)"
            ),
            severityTypical = DiseaseSeverity.SEVERE
        ),

        // HEALTHY STATUS
        Disease(
            id = "healthy_crop",
            cropId = "all",
            nameEn = "Healthy & Vigor Vigorous",
            nameAm = "ጤናማ እና ለምለም ሰብል",
            scientificName = "No active pathogen detected",
            pathogenType = PathogenType.HEALTHY,
            affectedOrgans = listOf(PlantOrgan.WHOLE_PLANT),
            symptomsEn = listOf(
                "Vibrant deep green foliage with uniform turgor",
                "Clean stems without necrotic vascular discoloration or lesions",
                "Normal growth rate appropriate for current physiological growth stage"
            ),
            symptomsAm = listOf(
                "ደማቅ አረንጓዴ ቀለም ያለው ጤናማ ቅጠል",
                "ምንም ዓይነት የፈንገስ ወይም የነፍሳት ምልክት የሌለበት ግንድ",
                "ጥሩ የእድገት ደረጃ"
            ),
            favorableConditions = "Optimal soil moisture, balanced nutrition, and clean crop husbandry",
            organicTreatmentsEn = listOf(
                "Continue standard cultural management and routine weeding",
                "Apply organic mulch or top-dress with compost to preserve moisture"
            ),
            organicTreatmentsAm = listOf(
                "የተለመደውን የእንክብካቤ ስራ መቀጠል",
                "አረም በወቅቱ ማረም"
            ),
            chemicalTreatmentsEn = listOf(
                "No chemical pesticides or fungicides required. Avoid unnecessary chemical applications."
            ),
            chemicalTreatmentsAm = listOf(
                "ምንም ዓይነት ኬሚካል አያስፈልግም"
            ),
            preventionMethodsEn = listOf(
                "Continue weekly scout patrols to detect initial pest or pathogen flushes early"
            ),
            preventionMethodsAm = listOf(
                "በየሳምንቱ ማሳውን መጎብኘት"
            ),
            severityTypical = DiseaseSeverity.HEALTHY,
            daConsultationAdvised = false
        )
    )

    val WEATHER_STATIONS = listOf(
        EthiopianRegionWeather(
            regionName = "Oromia",
            zoneName = "Arsi (Asella / Tiyo)",
            seasonName = "Meher (መኸር)",
            temperatureC = 21,
            condition = "Partly Cloudy with Evening Rains",
            humidityPercent = 84,
            rainProbability = 75,
            windKmh = 12,
            fungalDiseaseRiskLevel = "High",
            advisoryNoticeEn = "High humidity (84%) increases Wheat Stem Rust and Blight spore dispersion. Inspect wheat fields daily.",
            advisoryNoticeAm = "ከፍተኛ እርጥበት (84%) የስንዴ ዋግ በሽታን ያባብሳል፤ ማሳዎን በየቀኑ ይፈትሹ።"
        ),
        EthiopianRegionWeather(
            regionName = "Amhara",
            zoneName = "East Gojjam (Debre Markos)",
            seasonName = "Meher (መኸር)",
            temperatureC = 19,
            condition = "Intermittent Showers",
            humidityPercent = 88,
            rainProbability = 80,
            windKmh = 9,
            fungalDiseaseRiskLevel = "Severe",
            advisoryNoticeEn = "High risk for Late Blight on Potatoes and Tomatoes. Avoid chemical spraying during active rains.",
            advisoryNoticeAm = "በድንችና ቲማቲም ላይ የብላይት በሽታ ስጋት ከፍተኛ ነው። ዝናብ እያዘነበ ኬሚካል አይርጩ።"
        ),
        EthiopianRegionWeather(
            regionName = "Sidama",
            zoneName = "Hawassa Zuria",
            seasonName = "Belg / Post-Belg",
            temperatureC = 25,
            condition = "Warm & Humid",
            humidityPercent = 68,
            rainProbability = 35,
            windKmh = 14,
            fungalDiseaseRiskLevel = "Moderate",
            advisoryNoticeEn = "Optimal conditions for Fall Armyworm scouting in young Maize. Inspect central whorls.",
            advisoryNoticeAm = "በቆሎ ላይ የመኸር ሰራዊት አባጨጓሬ እንዳይከሰት እምቡጦቹን ይመርምሩ።"
        ),
        EthiopianRegionWeather(
            regionName = "Oromia",
            zoneName = "Jimma (Manna / Gomma)",
            seasonName = "Meher (መኸር)",
            temperatureC = 23,
            condition = "Afternoon Rain & Mist",
            humidityPercent = 89,
            rainProbability = 85,
            windKmh = 8,
            fungalDiseaseRiskLevel = "High",
            advisoryNoticeEn = "Critical period for Coffee Berry Disease (CBD). Ensure shade trees are trimmed for airflow.",
            advisoryNoticeAm = "የቡና ፍሬ ማበስበስ በሽታ (CBD) የሚከሰትበት ወቅት ስለሆነ የጥላ ዛፎችን ይግረዙ።"
        ),
        EthiopianRegionWeather(
            regionName = "Tigray",
            zoneName = "Central Tigray (Axum / Adwa)",
            seasonName = "Belg Transition",
            temperatureC = 27,
            condition = "Dry & Sunny",
            humidityPercent = 38,
            rainProbability = 15,
            windKmh = 18,
            fungalDiseaseRiskLevel = "Low",
            advisoryNoticeEn = "Low fungal risk. Ideal spraying window if pest interventions are scheduled.",
            advisoryNoticeAm = "የፈንገስ ስጋት ዝቅተኛ ነው። ፀረ-ተባይ ለመርጨት ተስማሚ አየር ነው።"
        )
    )

    val KNOWLEDGE_ARTICLES = listOf(
        KnowledgeArticle(
            id = "guide_wheat_rust",
            titleEn = "Comprehensive Guide to Managing Wheat Stem Rust (Ug99) in Ethiopian Highlands",
            titleAm = "በኢትዮጵያ ደጋማ አካባቢዎች የስንዴ ግንድ ዋግ (Ug99) በሽታ አያያዝ መመሪያ",
            cropName = "Wheat",
            category = "Disease Management",
            readTimeMinutes = 5,
            summaryEn = "Learn how to scout, differentiate rust pathotypes, and deploy early fungicide interventions in Arsi-Bale wheat belts.",
            summaryAm = "በአርሲና ባሌ የስንዴ ቀጠናዎች የዋግ በሽታን አስቀድሞ የመለየት እና ፀረ-ፈንገስ በወቅቱ የመጠቀም ዘዴ።",
            fullContentEn = """
                Wheat stem rust (Puccinia graminis) remains one of the most destructive agricultural threats to Ethiopian smallholders. 
                
                Key Symptoms:
                Reddish-brown, elongated, raised pustules (uredinia) that rupture the stem epidermis. When rubbed, brick-red spores rub onto fingers.
                
                Timing & Weather triggers:
                Stem rust requires free water on leaves or stems for 6-8 hours and temperatures between 18°C and 30°C. Heavy morning dews in September and October are prime trigger windows.
                
                Action Protocol:
                1. Plant certified resistant seeds: Replace susceptible older varieties with Kakaba, Danda'a, Kingbird, or Utuba.
                2. Scout every 3 days during the heading to dough stages.
                3. If even 2-3 pustules appear per 10 stems, spray immediately with Tebuconazole (Folicur 250 EC) or Propiconazole (Tilt 250 EC) at 500 ml per hectare mixed in 200 liters of water.
                4. Wear rubber boots, gloves, and face masks during spraying.
            """.trimIndent(),
            fullContentAm = """
                የስንዴ ግንድ ዋግ (Puccinia graminis) በኢትዮጵያ አርሶ አደሮች ላይ ከፍተኛ የምርት ኪሳራ ከሚያስከትሉ በሽታዎች አንዱ ነው።
                
                ዋነኛ ምልክቶች፡
                በስንዴው ግንድ ላይ የጡብ ቀለም ያላቸው የፈንገስ እብጠቶች መታየት፤ በእጅ ሲነኩ የቡናማ ዱቄት ብናኝ ይተዋሉ።
                
                መፍትሄዎች፡
                1. ተቋቋሚ ዝርያዎችን (ካካባ፣ ዳንዳአ፣ ኡቱባ) መዝራት።
                2. በየ 3 ቀኑ ማሳን መፈተሽ።
                3. ምልክት እንደታየ በፍጥነት ቴቡኮናዞል (Folicur) 500 ሚሊ በሄክታር መርጨት።
            """.trimIndent(),
            keyTakeawaysEn = listOf(
                "Early detection before grain filling prevents 80% of yield losses",
                "Ug99 can overcome older resistance genes; always plant verified seed",
                "Spraying must be done in calm morning weather without wind"
            ),
            keyTakeawaysAm = listOf(
                "በሽታውን በጊዜ ማግኘት እስከ 80% የሚደርስ ኪሳራን ያድናል",
                "የተመሰከረላቸውን አዳዲስ የስንዴ ዝርያዎች ብቻ መጠቀም",
                "መርጨት ያለበት ነፋስ በሌለበት የጠዋት ሰዓት ነው"
            )
        ),
        KnowledgeArticle(
            id = "guide_enset_bxw",
            titleEn = "Eradicating Enset Bacterial Wilt: Community Phytosanitary Protocols",
            titleAm = "የእንሰት ባክቴሪያ አገርሽ በሽታን የማጥፊያ ህብረተሰብ አቀፍ መመሪያ",
            cropName = "Enset",
            category = "Disease Management",
            readTimeMinutes = 4,
            summaryEn = "Why chemical sprays fail against BXW and how tool sanitization, fencing, and prompt rogueing save the farm.",
            summaryAm = "ባክቴሪያው በመድሃኒት ስለማይጠፋ መቁረጫ መሳሪያዎችን በእሳትና በረኪና በማጽዳት ማሳን የማዳን ዘዴ።",
            fullContentEn = """
                Enset (Ensete ventricosum) is the food security backbone for 20+ million citizens in southern Ethiopia. Bacterial wilt (Xanthomonas vasicola pv. musacearum) attacks the vascular core.
                
                Transmission:
                - Cutting tools (machetes, sickles) moving bacteria from sick plants to healthy ones.
                - Browsing oxen, sheep, or wild porcupines chewing on infected leaves.
                
                Zero Tolerance Rule:
                There is NO chemical that can cure an enset plant once the heart leaf wilts.
                1. Dig out the entire infected enset plant including the corm root.
                2. Chop it into small pieces within a deep trench on-site and bury it, or allow it to dry thoroughly in sunshine away from fields.
                3. Flame your knives over fire or dip into 10% household bleach for 30 seconds after touching any enset plant.
            """.trimIndent(),
            fullContentAm = """
                እንሰት በደቡብ ኢትዮጵያ ለሚኖሩ ከ20 ሚሊዮን በላይ ዜጎች ዋነኛ የምግብ ዋስትና ነው። ባክቴሪያው ግንዱንና ስሩን ያበሰብሳል።
                
                መተላለፊያ መንገዶች፡
                - የታመመ እንሰት የተቆረጠበት ቢላዋ ወይም ቆንጨራ ሳይጸዳ ሌላውን ሲቆርጥ።
                - ከብቶች የታመመውን ነክሰው ወደ ጤነኛው ሲዛመቱ።
                
                መፍትሄ፡
                ለዚህ በሽታ መድሃኒት የለም! የታመመውን ወዲያውኑ ነቅሎ መቅበር እና ቢላዎችን በእሳት ማጋል ወይም በበረኪና ማጽዳት ግዴታ ነው።
            """.trimIndent(),
            keyTakeawaysEn = listOf(
                "Disinfect machetes with fire or bleach between every enset cut",
                "Fence enset plantations against wandering livestock",
                "Never leave rotten enset stumps in the ground"
            ),
            keyTakeawaysAm = listOf(
                "ቢላዎችን በእሳት ወይም በበረኪና ሳያጸዱ ጤነኛውን እንሰት አይቁረጡ",
                "ከብቶች እንዳይገቡ ማሳውን ያጥሩ",
                "የበሰበሰውን የእንሰት ጉቶ መሬት ውስጥ አይተዉት"
            )
        ),
        KnowledgeArticle(
            id = "guide_pesticide_safety",
            titleEn = "Safe Pesticide Handling, Protective Gear (PPE) & Re-entry Intervals",
            titleAm = "የፀረ-ተባይ ኬሚካሎች አጠቃቀም፣ የመከላከያ አልባሳትና የጥንቃቄ መመሪያ",
            cropName = "All Crops",
            category = "Safe Pesticides",
            readTimeMinutes = 6,
            summaryEn = "Essential safety standards to protect farm families, livestock, water sources, and bee populations in Ethiopia.",
            summaryAm = "የቤተሰብ፣ የእንስሳት፣ የውሃና የንብ ጤናን ለመጠበቅ የሚረዱ የኬሚካል ጥንቃቄ ህጎች።",
            fullContentEn = """
                Pesticides, insecticides, and fungicides are potent agricultural chemicals requiring disciplined safety standards.
                
                Safety Rules:
                1. ALWAYS check the color band on the label (Red = Extremely Hazardous; Yellow = Moderately Hazardous; Blue/Green = Caution).
                2. Wear long-sleeved clothes, rubber boots, nitrile gloves, and a face mask or cloth covering mouth and nose.
                3. NEVER blow into clogged spray nozzles with your mouth; clear with a grass blade or wire.
                4. Spray with the wind at your back to prevent drift into your face.
                5. Do NOT wash knapsack sprayers in rivers, streams, or community drinking ponds.
                6. Observe Pre-Harvest Intervals (PHI): wait the required number of days between spraying and harvesting tomatoes or vegetables (usually 7–14 days).
            """.trimIndent(),
            fullContentAm = """
                የግብርና ኬሚካሎች ለሰውም ሆነ ለእንስሳት ጎጂ ስለሆኑ ከፍተኛ ጥንቃቄ ያስፈልጋቸዋል።
                
                ዋና ዋና ህጎች፡
                1. ረጅም እጅጌ ያለው ልብስ፣ የጎማ ጓንት፣ ቡትስ ጫማና አፍና አፍንጫ መሸፈኛ መጠቀም።
                2. የዘጋውን የመርጫ ቀዳዳ በአፍ አለመንፋት።
                3. ነፋሱ ወደሚነፍስበት አቅጣጫ መርጨት (ወደ ፊት እንዳይመጣ)።
                4. መርጫውን በወንዝ ወይም በምንጭ ውሃ ውስጥ አለማጠብ።
                5. አትክልት ላይ ኬሚካል ከተረጨ በኋላ የመሰብሰቢያውን ቀን መጠበቅ (ከ7-14 ቀን በኋላ መሸጥና መመገብ)።
            """.trimIndent(),
            keyTakeawaysEn = listOf(
                "Wash hands and face with soap immediately after spraying",
                "Triple rinse empty chemical containers and puncture them; never use for drinking water",
                "Keep all agricultural chemicals locked away from children and grain stores"
            ),
            keyTakeawaysAm = listOf(
                "መርጨት እንደጨረሱ እጅና ፊትን በሳሙና በደንብ መታጠብ",
                "ባዶ የኬሚካል እቃዎችን ለውሃ መጠጫነት በፍጹም አለመጠቀም",
                "ኬሚካሎችን ከልጆችና ከምግብ እህል ማራቅ"
            )
        )
    )

    val INITIAL_CONSULTATIONS = listOf(
        Consultation(
            id = 1,
            farmerName = "Abebe Bekele",
            location = "Oromia, Arsi (Tiyo Woreda)",
            cropName = "Wheat",
            issueTitle = "Brick-red dust on wheat stalks after heavy rain",
            description = "My wheat in parcel 2 is heading. Yesterday I noticed red powdery pustules along the stems. Is this stem rust? Should I spray Folicur now?",
            imageUri = null,
            aiSuggestedDisease = "Wheat Stem Rust (Ug99)",
            status = ConsultationStatus.PRESCRIBED.name,
            expertName = "Dr. Dawit Mengistu (Senior Plant Pathologist)",
            expertResponse = "Verified as early stage Stem Rust (Puccinia graminis). Do not delay. Apply Folicur 250 EC at 500 ml/ha with 200 L of water before 10 AM. Re-inspect after 10 days.",
            prescribedDosage = "Folicur 250 EC: 500 ml/ha in 200 L water",
            timestamp = System.currentTimeMillis() - 86400000L * 2
        ),
        Consultation(
            id = 2,
            farmerName = "Tirunesh Desta",
            location = "Amhara, East Gojjam",
            cropName = "Tomato",
            issueTitle = "Dark wet spots on leaves and fruit during July rain",
            description = "The tomato leaves are turning black and greasy. Several green fruits also have firm brown depressions. Please advise.",
            imageUri = null,
            aiSuggestedDisease = "Tomato Late Blight",
            status = ConsultationStatus.UNDER_REVIEW.name,
            expertName = "Extension Agent Kassahun",
            expertResponse = "Case under evaluation by Gojjam Agricultural Extension. Preliminary advice: cease overhead irrigation and prune bottom leaves.",
            prescribedDosage = "Pending site verification",
            timestamp = System.currentTimeMillis() - 86400000L
        )
    )

    val INITIAL_PARCELS = listOf(
        FarmParcel(
            id = 1,
            parcelName = "Bishoftu Parcel 1 (Upper Field)",
            region = "Oromia",
            zone = "East Shewa (Ada'a)",
            areaHectares = 1.75,
            activeCropName = "Teff (Quncho)",
            plantingDateEpoch = System.currentTimeMillis() - 86400000L * 45,
            growthStage = "Flowering / Heading",
            lastFertilizerType = "NPS (100 kg/ha) at planting",
            healthStatus = "Good",
            notes = "Good germination; scouted yesterday for teff rust."
        ),
        FarmParcel(
            id = 2,
            parcelName = "Arsi Highland Parcel 2",
            region = "Oromia",
            zone = "Arsi (Tiyo)",
            areaHectares = 2.50,
            activeCropName = "Wheat (Danda'a)",
            plantingDateEpoch = System.currentTimeMillis() - 86400000L * 60,
            growthStage = "Grain Filling",
            lastFertilizerType = "Urea Top-Dress (50 kg/ha)",
            healthStatus = "Under Treatment",
            notes = "Mild rust detected on lower stalks; preventative spray applied."
        ),
        FarmParcel(
            id = 3,
            parcelName = "Meki Valley Plot",
            region = "Oromia",
            zone = "Rift Valley",
            areaHectares = 0.80,
            activeCropName = "Tomato (Galilea)",
            plantingDateEpoch = System.currentTimeMillis() - 86400000L * 30,
            growthStage = "Vegetative",
            lastFertilizerType = "Compost + NPS",
            healthStatus = "Excellent",
            notes = "Trellised tomatoes on drip irrigation."
        )
    )
}
