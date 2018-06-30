import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class transLinesToNetwork {


    //构建停用词表和停用词类型的Map结构
    String stopwords = ":\t~\t顾客\t客服\t啊\t阿\t哎\t哎呀\t哎哟\t唉\t俺\t俺们\t按\t按照\t吧\t吧哒\t把\t罢了\t被\t本\t本着\t比\t比方\t比如\t鄙人\t彼\t彼此\t边\t别\t别的\t别说\t并\t并且\t不比\t不成\t不单\t不但\t不独\t不管\t不光\t不过\t不仅\t不拘\t不论\t不怕\t不然\t不如\t不特\t不惟\t不问\t不只\t朝\t朝着\t趁\t趁着\t乘\t冲\t除\t除此之外\t除非\t除了\t此\t此间\t此外\t从\t从而\t打\t待\t但\t但是\t当\t当着\t到\t得\t的\t的话\t等\t等等\t地\t第\t叮咚\t对\t对于\t多\t多少\t而\t而况\t而且\t而是\t而外\t而言\t而已\t尔后\t反过来\t反过来说\t反之\t非但\t非徒\t否则\t嘎\t嘎登\t该\t赶\t个\t各\t各个\t各位\t各种\t各自\t给\t根据\t跟\t故\t故此\t固然\t关于\t管\t归\t果然\t果真\t过\t哈\t哈哈\t呵\t和\t何\t何处\t何况\t何时\t嘿\t哼\t哼唷\t呼哧\t乎\t哗\t还是\t还有\t换句话说\t换言之\t或\t或是\t或者\t极了\t及\t及其\t及至\t即\t即便\t即或\t即令\t即若\t即使\t几\t几时\t己\t既\t既然\t既是\t继而\t加之\t假如\t假若\t假使\t鉴于\t将\t较\t较之\t叫\t接着\t结果\t借\t紧接着\t进而\t尽\t尽管\t经\t经过\t就\t就是\t就是说\t据\t具体地说\t具体说来\t开始\t开外\t靠\t咳\t可\t可见\t可是\t可以\t况且\t啦\t来\t来着\t离\t例如\t哩\t连\t连同\t两者\t了\t临\t另\t另外\t另一方面\t论\t嘛\t吗\t慢说\t漫说\t冒\t么\t每\t每当\t们\t莫若\t某\t某个\t某些\t拿\t哪\t哪边\t哪儿\t哪个\t哪里\t哪年\t哪怕\t哪天\t哪些\t哪样\t那\t那边\t那儿\t那个\t那会儿\t那里\t那么\t那么些\t那么样\t那时\t那些\t那样\t乃\t乃至\t呢\t能\t你\t你们\t您\t宁\t宁可\t宁肯\t宁愿\t哦\t呕\t啪达\t旁人\t呸\t凭\t凭借\t其\t其次\t其二\t其他\t其它\t其一\t其余\t其中\t起\t起见\t岂但\t恰恰相反\t前后\t前者\t且\t然而\t然后\t然则\t让\t人家\t任\t任何\t任凭\t如\t如此\t如果\t如何\t如其\t如若\t如上所述\t若\t若非\t若是\t啥\t上下\t尚且\t设若\t设使\t甚而\t甚么\t甚至\t省得\t时候\t什么\t什么样\t使得\t是\t是的\t首先\t谁\t谁知\t顺\t顺着\t似的\t虽\t虽然\t虽说\t虽则\t随\t随着\t所\t所以\t他\t他们\t他人\t它\t它们\t她\t她们\t倘\t倘或\t倘然\t倘若\t倘使\t腾\t替\t通过\t同\t同时\t哇\t万一\t往\t望\t为\t为何\t为了\t为什么\t为着\t喂\t嗡嗡\t我\t我们\t呜\t呜呼\t乌乎\t无论\t无宁\t毋宁\t嘻\t吓\t相对而言\t像\t向\t向着\t嘘\t呀\t焉\t沿\t沿着\t要\t要不\t要不然\t要不是\t要么\t要是\t也\t也罢\t也好\t一\t一般\t一旦\t一方面\t一来\t一切\t一样\t一则\t依\t依照\t矣\t以\t以便\t以及\t以免\t以至\t以至于\t以致\t抑或\t因\t因此\t因而\t因为\t哟\t用\t由\t由此可见\t由于\t有\t有的\t有关\t有些\t又\t于\t于是\t于是乎\t与\t与此同时\t与否\t与其\t越是\t云云\t哉\t再说\t再者\t在\t在下\t咱\t咱们\t则\t怎\t怎么\t怎么办\t怎么样\t怎样\t咋\t照\t照着\t者\t这\t这边\t这儿\t这个\t这会儿\t这就是说\t这里\t这么\t这么点儿\t这么些\t这么样\t这时\t这些\t这样\t正如\t吱\t之\t之类\t之所以\t之一\t只是\t只限\t只要\t只有\t至\t至于\t诸位\t着\t着呢\t自\t自从\t自个儿\t自各儿\t自己\t自家\t自身\t综上所述\t总的来看\t总的来说\t总的说来\t总而言之\t总之\t纵\t纵令\t纵然\t纵使\t遵照\t作为\t兮\t呃\t呗\t咚\t咦\t喏\t啐\t喔唷\t嗬\t嗯\t嗳\t啊哈\t啊呀\t啊哟\t挨次\t挨个\t挨家挨户\t挨门挨户\t挨门逐户\t挨着\t按理\t按期\t按时\t按说\t暗地里\t暗中\t暗自\t昂然\t八成\t白白\t半\t梆\t保管\t保险\t饱\t背地里\t背靠背\t倍感\t倍加\t本人\t本身\t甭\t比起\t比如说\t比照\t毕竟\t必\t必定\t必将\t必须\t便\t别人\t并非\t并肩\t并没\t并没有\t并排\t并无\t勃然\t不\t不必\t不常\t不大\t不得\t不得不\t不得了\t不得已\t不迭\t不定\t不对\t不妨\t不管怎样\t不会\t不仅仅\t不仅仅是\t不经意\t不可开交\t不可抗拒\t不力\t不了\t不料\t不满\t不免\t不能不\t不起\t不巧\t不然的话\t不日\t不少\t不胜\t不时\t不是\t不同\t不能\t不要\t不外\t不外乎\t不下\t不限\t不消\t不已\t不亦乐乎\t不由得\t不再\t不择手段\t不怎么\t不曾\t不知不觉\t不止\t不止一次\t不至于\t才\t才能\t策略地\t差不多\t差一点\t常\t常常\t常言道\t常言说\t常言说得好\t长此下去\t长话短说\t长期以来\t长线\t敞开儿\t彻夜\t陈年\t趁便\t趁机\t趁热\t趁势\t趁早\t成年\t成年累月\t成心\t乘机\t乘胜\t乘势\t乘隙\t乘虚\t诚然\t迟早\t充分\t充其极\t充其量\t抽冷子\t臭\t初\t出\t出来\t出去\t除此\t除此而外\t除此以外\t除开\t除去\t除却\t除外\t处处\t川流不息\t传\t传说\t传闻\t串行\t纯\t纯粹\t此后\t此中\t次第\t匆匆\t从不\t从此\t从此以后\t从古到今\t从古至今\t从今以后\t从宽\t从来\t从轻\t从速\t从头\t从未\t从无到有\t从小\t从新\t从严\t从优\t从早到晚\t从中\t从重\t凑巧\t粗\t存心\t达旦\t打从\t打开天窗说亮话\t大\t大不了\t大大\t大抵\t大都\t大多\t大凡\t大概\t大家\t大举\t大略\t大面儿上\t大事\t大体\t大体上\t大约\t大张旗鼓\t大致\t呆呆地\t带\t殆\t待到\t单\t单纯\t单单\t但愿\t弹指之间\t当场\t当儿\t当即\t当口儿\t当然\t当庭\t当头\t当下\t当真\t当中\t倒不如\t倒不如说\t倒是\t到处\t到底\t到了儿\t到目前为止\t到头\t到头来\t得起\t得天独厚\t的确\t等到\t叮当\t顶多\t定\t动不动\t动辄\t陡然\t都\t独\t独自\t断然\t顿时\t多次\t多多\t多多少少\t多多益善\t多亏\t多年来\t多年前\t而后\t而论\t而又\t尔等\t二话不说\t二话没说\t反倒\t反倒是\t反而\t反手\t反之亦然\t反之则\t方\t方才\t方能\t放量\t非常\t非得\t分期\t分期分批\t分头\t奋勇\t愤然\t风雨无阻\t逢\t弗\t甫\t嘎嘎\t该当\t概\t赶快\t赶早不赶晚\t敢\t敢情\t敢于\t刚\t刚才\t刚好\t刚巧\t高低\t格外\t隔日\t隔夜\t个人\t各式\t更\t更加\t更进一步\t更为\t公然\t共\t共总\t够瞧的\t姑且\t古来\t故而\t故意\t固\t怪\t怪不得\t惯常\t光\t光是\t归根到底\t归根结底\t过于\t毫不\t毫无\t毫无保留地\t毫无例外\t好在\t何必\t何尝\t何妨\t何苦\t何乐而不为\t何须\t何止\t很\t很多\t很少\t轰然\t后来\t呼啦\t忽地\t忽然\t互\t互相\t哗啦\t话说\t还\t恍然\t会\t豁然\t活\t伙同\t或多或少\t或许\t基本\t基本上\t基于\t极\t极大\t极度\t极端\t极力\t极其\t极为\t急匆匆\t即将\t即刻\t即是说\t几度\t几番\t几乎\t几经\t既...又\t继之\t加上\t加以\t间或\t简而言之\t简言之\t简直\t见\t将才\t将近\t将要\t交口\t较比\t较为\t接连不断\t接下来\t皆可\t截然\t截至\t藉以\t借此\t借以\t届时\t仅\t仅仅\t谨\t进来\t进去\t近\t近几年来\t近来\t近年来\t尽管如此\t尽可能\t尽快\t尽量\t尽然\t尽如人意\t尽心竭力\t尽心尽力\t尽早\t精光\t经常\t竟\t竟然\t究竟\t就此\t就地\t就算\t居然\t局外\t举凡\t据称\t据此\t据实\t据说\t据我所知\t据悉\t具体来说\t决不\t决非\t绝\t绝不\t绝顶\t绝对\t绝非\t均\t喀\t看\t看来\t看起来\t看上去\t看样子\t可好\t可能\t恐怕\t快\t快要\t来不及\t来得及\t来讲\t来看\t拦腰\t牢牢\t老\t老大\t老老实实\t老是\t累次\t累年\t理当\t理该\t理应\t历\t立\t立地\t立刻\t立马\t立时\t联袂\t连连\t连日\t连日来\t连声\t连袂\t临到\t另方面\t另行\t另一个\t路经\t屡\t屡次\t屡次三番\t屡屡\t缕缕\t率尔\t率然\t略\t略加\t略微\t略为\t论说\t马上\t蛮\t满\t没\t没有\t每逢\t每每\t每时每刻\t猛然\t猛然间\t莫\t莫不\t莫非\t莫如\t默默地\t默然\t呐\t那末\t奈\t难道\t难得\t难怪\t难说\t内\t年复一年\t凝神\t偶而\t偶尔\t怕\t砰\t碰巧\t譬如\t偏偏\t乒\t平素\t颇\t迫于\t扑通\t其后\t其实\t奇\t齐\t起初\t起来\t起首\t起头\t起先\t岂\t岂非\t岂止\t迄\t恰逢\t恰好\t恰恰\t恰巧\t恰如\t恰似\t千\t万\t千万\t千万千万\t切\t切不可\t切莫\t切切\t切勿\t窃\t亲口\t亲身\t亲手\t亲眼\t亲自\t顷\t顷刻\t顷刻间\t顷刻之间\t请勿\t穷年累月\t取道\t去\t权时\t全都\t全力\t全年\t全然\t全身心\t然\t人人\t仍\t仍旧\t仍然\t日复一日\t日见\t日渐\t日益\t日臻\t如常\t如此等等\t如次\t如今\t如期\t如前所述\t如上\t如下\t汝\t三番两次\t三番五次\t三天两头\t瑟瑟\t沙沙\t上\t上来\t上去\t一.\t一一\t一下\t一个\t一些\t一何\t一则通过\t一天\t一定\t一时\t一次\t一片\t一番\t一直\t一致\t一起\t一转眼\t一边\t一面\t上升\t上述\t上面\t下\t下列\t下去\t下来\t下面\t不一\t不久\t不变\t不可\t不够\t不尽\t不尽然\t不敢\t不断\t不若\t不足\t与其说\t专门\t且不说\t且说\t严格\t严重\t个别\t中小\t中间\t丰富\t为主\t为什麽\t为止\t为此\t主张\t主要\t举行\t乃至于\t之前\t之后\t之後\t也就是说\t也是\t了解\t争取\t二来\t云尔\t些\t亦\t产生\t人\t人们\t什麽\t今\t今后\t今天\t今年\t今後\t介于\t从事\t他是\t他的\t代替\t以上\t以下\t以为\t以前\t以后\t以外\t以後\t以故\t以期\t以来\t任务\t企图\t伟大\t似乎\t但凡\t何以\t余外\t你是\t你的\t使\t使用\t依据\t依靠\t便于\t促进\t保持\t做到\t傥然\t儿\t允许\t元／吨\t先不先\t先后\t先後\t先生\t全体\t全部\t全面\t共同\t具体\t具有\t兼之\t再\t再其次\t再则\t再有\t再次\t再者说\t决定\t准备\t凡\t凡是\t出于\t出现\t分别\t则甚\t别处\t别是\t别管\t前此\t前进\t前面\t加入\t加强\t十分\t即如\t却\t却不\t原来\t又及\t及时\t双方\t反应\t反映\t取得\t受到\t变成\t另悉\t只\t只当\t只怕\t只消\t叫做\t召开\t各人\t各地\t各级\t合理\t同一\t同样\t后\t后者\t后面\t向使\t周围\t呵呵\t咧\t唯有\t啷当\t喽\t嗡\t嘿嘿\t因了\t因着\t在于\t坚决\t坚持\t处在\t处理\t复杂\t多么\t多数\t大力\t大多数\t大批\t大量\t失去\t她是\t她的\t好\t好的\t好象\t如同\t如是\t始而\t存在\t孰料\t孰知\t它们的\t它是\t它的\t安全\t完全\t完成\t实现\t实际\t宣布\t容易\t密切\t对应\t对待\t对方\t对比\t小\t少数\t尔\t尔尔\t尤其\t就是了\t就要\t属于\t左右\t巨大\t巩固\t已\t已矣\t已经\t巴\t巴巴\t帮助\t并不\t并不是\t广大\t广泛\t应当\t应用\t应该\t庶乎\t庶几\t开展\t引起\t强烈\t强调\t归齐\t当前\t当地\t当时\t形成\t彻底\t彼时\t往往\t後来\t後面\t得了\t得出\t得到\t心里\t必然\t必要\t怎奈\t怎麽\t总是\t总结\t您们\t您是\t惟其\t意思\t愿意\t成为\t我是\t我的\t或则\t或曰\t战斗\t所在\t所幸\t所有\t所谓\t扩大\t掌握\t接著\t数/\t整个\t方便\t方面\t无\t无法\t既往\t明显\t明确\t是不是\t是以\t是否\t显然\t显著\t普通\t普遍\t曾\t曾经\t替代\t最\t最后\t最大\t最好\t最後\t最近\t最高\t有利\t有力\t有及\t有所\t有效\t有时\t有点\t有的是\t有着\t有著\t末##末\t本地\t来自\t来说\t构成\t某某\t根本\t欢迎\t欤\t正值\t正在\t正巧\t正常\t正是\t此地\t此处\t此时\t此次\t每个\t每天\t每年\t比及\t比较\t没奈何\t注意\t深入\t清楚\t满足\t然後\t特别是\t特殊\t特点\t犹且\t犹自\t现代\t现在\t甚且\t甚或\t甚至于\t用来\t由是\t由此\t目前\t直到\t直接\t相似\t相信\t相反\t相同\t相对\t相应\t相当\t相等\t看出\t看到\t看看\t看见\t真是\t真正\t眨眼\t矣乎\t矣哉\t知道\t确定\t种\t积极\t移动\t突出\t突然\t立即\t竟而\t第二\t类如\t练习\t组成\t结合\t继后\t继续\t维持\t考虑\t联系\t能否\t能够\t自后\t自打\t至今\t至若\t致\t般的\t良好\t若夫\t若果\t范围\t莫不然\t获得\t行为\t行动\t表明\t表示\t要求\t规定\t觉得\t譬喻\t认为\t认真\t认识\t许多\t设或\t诚如\t说明\t说来\t说说\t诸\t诸如\t谁人\t谁料\t贼死\t赖以\t距\t转动\t转变\t转贴\t达到\t迅速\t过去\t过来\t运用\t还要\t这一来\t这次\t这点\t这种\t这般\t这麽\t进入\t进步\t进行\t适应\t适当\t适用\t逐步\t逐渐\t通常\t造成\t遇到\t遭到\t遵循\t避免\t那般\t那麽\t部分\t采取\t里面\t重大\t重新\t重要\t针对\t问题\t防止\t附近\t限制\t随后\t随时\t随著\t难道说\t集中\t需要\t非特\t非独\t高兴\t若果\t";
    String wordType = "w nx xu wyz wyy ww wt ws wp wn wm wkz wky wj wh wf wd wb w vshi uzhi uzhe uz wyy uv usuo uls ulian ule udeng udh ug uguo ude1 ude2 pbei pba o e m";
    Map<String,Integer> stopWordsList = new HashMap<>();
    Map<String,Integer> wordTypeList = new HashMap<>();

    public void buildStopWordsMap(){
        String[] sWords = stopwords.split("\t");
        for (int i = 0 ; i < sWords.length ; i++){
            stopWordsList.put(sWords[i],i);
        }String[] wType = wordType.split(" ");
        for (int i = 0 ; i < wType.length ; i++){
            wordTypeList.put(wType[i],i);
        }
    }

    //构建词和边存储结构
    Map<String,Integer> wordMap = new HashMap<>(); //词表，对应编号
    Map<String,Integer> edgeMap = new HashMap<>(); //边表，以wordNum1,wordNum2表示，对应value为权重
    Map<String,String> edgeMapWithID = new HashMap<>();

    public void readCorpus(String filepath) throws IOException {
        File inputFile = new File(filepath);
        if (inputFile.exists() && inputFile.isFile()){
            InputStreamReader fileReader = new InputStreamReader(new FileInputStream(inputFile));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String lineTxt = null;
            int count = 0;
            int lineCount = 0;
            while((lineTxt = bufferedReader.readLine()) != null){ //读取对应行，并拆分词表和存储边关系
                List<Term> termList = StandardTokenizer.segment(lineTxt);
                int[] words = new int[termList.size()]; //将句子改写为以词序号的序列
                for (int i = 0; i < termList.size(); i++){
                    String[] tmp = termList.get(i).toString().split("/");//对hanlp的分词结果进行处理
                    if ((!wordTypeList.containsKey(tmp[tmp.length-1])) && (!stopWordsList.containsKey(tmp[0]))) {//排除停用词
                        if (wordMap.containsKey(tmp[0])){ //如果该词已有序号
                            words[i] = wordMap.get(tmp[0]);
                        }
                        else { //如果该词还没有获得序号
                            wordMap.put(tmp[0],count);
                            words[i] = count;
                            count++;
                        }
                    }

                }

                //存储句子出现的词共现关系
                for (int i = 0 ; i < words.length - 1; i++){
                    for (int j = i+1 ; j < words.length ; j++){
                        if (words[i] != words[j]) {
                            String edge = words[i] < words[j] ? words[i] + "," + words[j] : words[j] + "," + words[i];
                            if (edgeMap.containsKey(edge)){
                                edgeMap.put(edge, edgeMap.get(edge) + 1);
                                edgeMapWithID.put(edge,edgeMapWithID.get(edge)+","+lineCount);
                            }
                            else{
                                edgeMap.put(edge, 1);
                                edgeMapWithID.put(edge,String.valueOf(lineCount));
                            }
                        }
                    }
                }
                lineCount++;

            }
            bufferedReader.close();
            fileReader.close();
        }
        System.out.println("总词数为 " + wordMap.size() + "。");
        System.out.println("总边数为 " + edgeMap.size() + " 条。");
    }





    public static void main(String[] args){
        System.out.println("Hello world");
        System.out.println(HanLP.segment("你好，欢迎使用HanLP汉语处理包！"));
    }
}
