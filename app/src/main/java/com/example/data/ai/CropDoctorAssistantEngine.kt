package com.example.data.ai

data class ChatMessage(
    val id: String = java.util.UUID.randomUUID().toString(),
    val isUser: Boolean,
    val textEn: String,
    val textAm: String,
    val timestamp: Long = System.currentTimeMillis()
)

object CropDoctorAssistantEngine {

    fun generateAssistantResponse(query: String, isAmharic: Boolean): ChatMessage {
        val q = query.lowercase().trim()

        val (en, am) = when {
            q.contains("yellow") || q.contains("ቢጫ") || q.contains("ቅጠል") -> {
                Pair(
                    "Yellowing leaves in cereals usually point to either Nitrogen deficiency (chlorosis starts on older bottom leaves in a V-shape down the midrib) or Fungal Blight/Rust if spots or halos accompany the yellowing. If soil is waterlogged after heavy Meher rain, root respiration ceases, causing temporary yellowing. Check for powdery pustules or cigar-shaped lesions.",
                    "የቅጠል ወደ ቢጫነት መቀየር በዋናነት የናይትሮጅን (ዩሪያ) ማዳበሪያ እጥረት ምልክት ነው፤ ቢጫነቱ ከስረኛው ቅጠል ጀምሮ በ'V' ቅርጽ ይሰራጫል። ነጠብጣቦች ካሉበት ግን የፈንገስ ወይም የዋግ በሽታ ሊሆን ይችላል። እንዲሁም በክረምቱ ከፍተኛ ውሃ በማሳው ላይ ከቆመ ስሩ አየር ስለሚያጣ ቅጠሉ ይጫጫል። የውሃ መፋሰሻ ይስሩ።"
                )
            }
            q.contains("rust") || q.contains("ዋግ") || q.contains("ስንዴ") || q.contains("wheat") -> {
                Pair(
                    "Wheat Stem Rust (Ug99) is highly aggressive under warm, humid highland weather. Reddish-brown powdery pustules rupture the stem. Action: Spray Folicur 250 EC (Tebuconazole) at 500 ml/ha or Tilt 250 EC immediately at first sighting. Re-apply after 14 days if rainfall persists. Avoid spraying in direct midday sun or high winds.",
                    "የስንዴ ግንድ ዋግ (Ug99) በሞቃታማና እርጥበታማ ደጋማ አካባቢ በፍጥነት ይዛመታል። በስንዴው ግንድ ላይ የጡብ ቀለም ያላቸው የፈንገስ እብጠቶች ይታያሉ። መፍትሄ፡ የመጀመሪያው ምልክት እንደታየ ቴቡኮናዞል (Folicur 250 EC) 500 ሚሊ በሄክታር በፍጥነት ይርጩ። ዝናብ ከቀጠለ ከ14 ቀን በኋላ በድጋሚ ይርጩ።"
                )
            }
            q.contains("maize") || q.contains("በቆሎ") || q.contains("armyworm") || q.contains("አባጨጓሬ") -> {
                Pair(
                    "For Fall Armyworm in Maize: Look inside the central leaf whorl for sawdust-like frass and window-pane feeding holes. For organic control, apply fine wood ash mixed with chili powder directly into the whorl in early mornings. For chemical control, apply Ampligo 150 ZC (200-300 ml/ha) directly targeted into whorls before larvae burrow deep.",
                    "ለበቆሎ መኸር ሰራዊት አባጨጓሬ፡ በእምቡጡ ውስጥ የእንጨት ፍቅፋቂ የሚመስል ዓይነ-ምድርና ቀዳዳዎችን ይፈትሹ። በባህላዊ መንገድ አመድ ከበርበሬ ዱቄት ጋር ቀላቅለው በማለዳ ወደ እምቡጡ ያፍሱ። በኬሚካል ደግሞ አምፕሊጎ (Ampligo 150 ZC) 200-300 ሚሊ በሄክታር በቀጥታ ወደ እምቡጡ ይርጩ።"
                )
            }
            q.contains("coffee") || q.contains("ቡና") || q.contains("cbd") -> {
                Pair(
                    "For Coffee Berry Disease (CBD) and Leaf Rust: High rainfall and dense shade trigger heavy spore spread. Prune shade trees to let 30-40% sunlight filter through. Spray Copper Oxychloride or Copper Hydroxide (Kocide) preventative before heavy rains begin. For CBD, pick and destroy mummified black berries from previous harvest.",
                    "ለቡና ፍሬ ማበስበስ (CBD) እና ቅጠል ዝገት፡ ከፍተኛ ዝናብና ወፍራም ጥላ በሽታውን ያባብሳሉ። የጥላ ዛፎችንና የቡናውን ቅርንጫፍ በመግረዝ ፀሐይና አየር እንዲያገኝ ያድርጉ። ክረምቱ ከመግባቱ በፊት የኮፐር ፀረ-ፈንገስ ይርጩ። የደረቁ ጥቁር ፍሬዎችን ለቅመው ያቃጥሉ።"
                )
            }
            q.contains("enset") || q.contains("እንሰት") -> {
                Pair(
                    "Enset Bacterial Wilt (BXW): Caution! There is NO chemical cure. If the heart leaf wilts and turns yellow with ooze, immediately uproot the entire plant and bury it in a deep pit. Always sterilize cutting knives in fire or with 10% household bleach between plants. Fence your enset garden to stop stray livestock from spreading bacteria.",
                    "የእንሰት ባክቴሪያ አገርሽ በሽታ፡ ጥንቃቄ! ለዚህ በሽታ ምንም ዓይነት መድሃኒት የለም። የውስጠኛው ለጋ ቅጠል ከጠወለገ ተክሉን ነቅለው ጉድጓድ ውስጥ ይቅበሩ። መቁረጫ ቢላዎችን በእሳት ወይም በበረኪና ሳያጸዱ ጤነኛውን እንሰት በፍጹም አይንኩ። ከብቶች እንዳይገቡ ማሳዎን ያጥሩ።"
                )
            }
            q.contains("tomato") || q.contains("ቲማቲም") || q.contains("blight") -> {
                Pair(
                    "Tomato Late Blight causes greasy water-soaked spots on leaves and firm brown fruit depressions. Avoid overhead watering. Stake plants off the ground. Spray Ridomil Gold (Metalaxyl + Mancozeb) at 2.5 kg/ha if blight has appeared, or preventative Daconil before cloudy rainy spells.",
                    "የቲማቲም ሌት ብላይት (ዋግ) በቅጠልና በፍሬ ላይ ጥቁር የውሃ መልክ ያለው ነጠብጣብ ያመጣል። ቅጠሉ ላይ ውሃ አያፍሱ፤ ቲማቲሙን በአጣና ያስሩ። ምልክቱ ከታየ ሪዶሚል ጎልድ (Ridomil Gold) 2.5 ኪሎ በሄክታር ይርጩ።"
                )
            }
            q.contains("pesticide") || q.contains("safety") || q.contains("ኬሚካል") -> {
                Pair(
                    "Pesticide Safety Rules: 1. Wear long sleeves, rubber boots, gloves, and face mask. 2. Never spray against the wind. 3. Do not wash sprayers in rivers or ponds used for livestock or drinking. 4. Observe the Pre-Harvest Interval (PHI) of 7-14 days before picking vegetables for consumption or market.",
                    "የኬሚካል ጥንቃቄ ህጎች፡ 1. ረጅም ልብስ፣ የጎማ ጓንት፣ ቡትስ ጫማና አፍ መሸፈኛ ያድርጉ። 2. ነፋስ ወደሚነፍስበት አቅጣጫ አይርጩ። 3. መርጫውን ወንዝ ወይም ምንጭ ውስጥ አያጥቡ። 4. አትክልት ላይ ኬሚካል ከተረጨ በኋላ የመሰብሰቢያውን ቀን (ከ7-14 ቀን) ይጠብቁ።"
                )
            }
            else -> {
                Pair(
                    "Thank you for contacting CropDoctor AI. To protect your crop, please tell me which crop you are cultivating (e.g. Teff, Maize, Wheat, Coffee, Tomato, Enset), the specific symptoms observed (spots, wilting, holes, yellowing), and your region. You can also scan a leaf photograph in the Plant Scanner tab for instant diagnosis.",
                    "ሰላም! ሰብልዎን ለመጠበቅ የትኛውን ሰብል እያመረቱ እንደሆነ (ጤፍ፣ በቆሎ፣ ስንዴ፣ ቡና፣ ቲማቲም፣ እንሰት)፣ የታየውን ምልክትና አካባቢዎን ይንገሩኝ። እንዲሁም በ'ተክል መመርመሪያ' ክፍል የቅጠሉን ፎቶ በማንሳት ፈጣን ምርመራ ማድረግ ይችላሉ።"
                )
            }
        }

        return ChatMessage(
            isUser = false,
            textEn = en,
            textAm = am
        )
    }
}
