import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

val persons = setOf<String>(
    "廉颇",
    "小乔",
    "赵云",
    "墨子",
    "妲己",
    "嬴政",
    "孙尚香",
    "鲁班七号",
    "庄周",
    "刘禅",
    "高渐离",
    "阿轲",
    "钟无艳",
    "孙膑",
    "扁鹊",
    "白起",
    "芈月",
    "吕布",
    "周瑜",
    "夏侯惇",
    "甄姬",
    "曹操",
    "典韦",
    "宫本武藏",
    "李白",
    "马可波罗",
    "狄仁杰",
    "达摩",
    "项羽",
    "武则天",
    "老夫子",
    "关羽",
    "貂蝉",
    "安琪拉",
    "程咬金",
    "露娜",
    "姜子牙",
    "刘邦",
    "韩信",
    "王昭君",
    "兰陵王",
    "花木兰",
    "张良",
    "不知火舞",
    "娜可露露",
    "橘右京",
    "亚瑟",
    "孙悟空",
    "牛魔",
    "后羿",
    "刘备",
    "张飞",
    "李元芳",
    "虞姬",
    "钟馗",
    "成吉思汗",
    "杨戬",
    "雅典娜",
    "蔡文姬",
    "太乙真人",
    "哪吒",
    "诸葛亮",
    "黄忠",
    "大乔",
    "东皇太一",
    "干将莫邪",
    "鬼谷子",
    "铠",
    "百里守约",
    "百里玄策",
    "苏烈",
    "梦奇",
    "女娲",
    "明世隐",
    "公孙离",
    "杨玉环",
    "裴擒虎",
    "弈星",
    "狂铁",
    "米莱狄",
    "元歌",
    "孙策",
    "司马懿",
    "盾山",
    "伽罗",
    "沈梦溪",
    "李信",
    "上官婉儿",
    "嫦娥",
    "猪八戒",
    "盘古",
    "瑶",
    "云中君",
    "曜",
    "马超",
    "西施",
    "鲁班大师",
    "蒙犽",
    "镜",
    "蒙恬",
    "阿古朵",
    "夏洛特",
    "澜",
    "司空震",
    "艾琳",
    "云缨",
    "金蝉",
    "暃",
    "桑启",
    "戈娅",
    "海月",
    "赵怀真",
    "莱西奥",
    "姬小满",
)
class SlotViewModel {
    private var coroutineScope = CoroutineScope(Dispatchers.IO)
    private var _person = MutableStateFlow("")
    val person = _person.asStateFlow()
    companion object {
        @Volatile
        private var instance: SlotViewModel? = null
        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: SlotViewModel().also { instance = it }
            }
    }
    fun choose() {
        val count = Random.nextInt(10, 20)
        coroutineScope.launch {
            for (i in 0..count) {
                _person.emit(persons.random())
                delay(400)
            }
        }
    }
}


fun viewModel(): SlotViewModel {
    return SlotViewModel.getInstance()
}