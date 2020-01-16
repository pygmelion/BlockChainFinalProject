package org.fisco.bcos.asset.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.channel.event.filter.EventLogPushWithDecodeCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int256;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class Asset extends Contract {
    public static String BINARY = "1f00051108601500d51610880ff8410bf0b15f0d80f515f35096500503fb78251650841005f8032ff8262030f51f005004100020065820501f002280015f50f39ff00657f0f1141fa58010198fb5050645001003fff00bf50f33578785366d6856285600f00501508f09f1045100405015006605f165500036f00ff806f52b00f080006bf705802d180004530005b0158f201900081f1f00018b000150fb0f890f076020311900ff09266bf560068f0f9019061f22b1005b65029f83201b0347014251050e2048ff96400020113fa51f005ff1f00911fff5f016810555d85064f118f6988758019916590018551f90045f6ff1f80056f8f40f8fff005665275061608002050f010039808915f0928b02795000f98ff010005058f6f60053593168568006730f50160ff281311185f5956df305f0f06000500ff151602500f880f0f0000d08f5e61f00f00ff081000df0e0158106f22042d0f83b000f921ff656054900200ff406055800003113333f60f00010f3015360f3408f9100f000868808e0200f6f10051600059f10805350525d094030552bf009860f500705805b4c0f161065005080f100b90010f0656006f08ff8faff076b51ff0882086f0018fd036c880ff18f11af7f0080c84f400faf603515001600f8f0ff020020f1550f500d100d091649507302007800f5085f00185b82f1f0080f7100c10010060ef9bb00006322560650143e6720f5f500b0f0f1f9100002f35fff5f80568ff14f5b000140f001f00f810fcc10106200000100886f440500f03f500980ff6509932603530f9060f0090f8347900005081800b9ff0b1f50002800f695601f01109f8531f76f1300f1f5f00110ff00d1055000000f265f8000fb55102ff7f03b983004130012100b2f161f0509660f90110150011051f051e85001616103575c0906ffa2216f500100416002f38fffc08463991f0550c5f05f00032105ff50f003bb80f10f1866610036409f43056053f8f00003730520036110510338845d000f990061805f91f655da1000f5f160f1815f098f0a650b11f9188000000a1016f36101e20fb2f08f260fa005f501f1106250000ff3950df5116025f90f5060fff00001000609f65b06920f8fff0055f0c800f5f40891010061200004510000200551ff1145000240250f0750061698d00065050007111057090f00004d610ff210756300258280ff0805d3508609f0f20110610f090f28df0f0c4015f1e2105f801f08800800760d05005a105f50890634f1850fd00f10118100002ff0f0608b6021f352f1f0289835110066500f008660460355f0010150551a908180031001238631f370296f0f6f821fa9f880880cb400005f9600720f8607a00526f65880010d608f800000681f0d6507b0001f9006ff510fd61408048a0560051058c15853ff6260006b82016003909106f501f00862ff157fb078100888900105f5060f05380f00fbff5030fff20a00800f32ffff1f58080f40310110256050f015006885f1f6510536ff0b12d105507800f80f5606f601ff53294fff001858a808f0651000066989bf100006189000f004504039560f63b0500f2076300ba0005f09802226ff5f8d51af886f48050050f1f08250568bf02ff800801513002050ffd00fd7666ff70ff67100060060026185290bf0069181f5f5819ff0f051200d90004103f51d9070509080000ff09310005560cf17f61ff10300f79f0ffe686255901802206f3711080d105061614f10001980518292110720d85d022000708e4f02b6f56d5270f5f06510001090810000143036030f004050f13006000f16408200050d0c0053b8c250f06f3611ffb087308011616045ef10ff600f8ff83900005f0500616011f0d06000300085003870200f02090f501f800780c151500988016705f2f00100140590b52140009b3150fb85b05f13f86f000111554009760006f68163d540600501d1950ff0002b5f0105080505501f610d11605f580f80660080f15006505f006af709f01405062494620600000fb002862d105000638634000f000785600000004f135b050ff15101050fdb250f950058ff07882101a035fff6f3505ff0f1868837bf000385ff001210f5206961b3560f51f0b10635f0167707f05b010f162f0690065860085700050000700001859f285fff00100f095f06ff304058058000860793930853f6040f8933000f0015b11f02fff85c500f046001fff80005760f0011cd57066f04b9b05a6004f0f800000f26719408101000005f1072f1359400f05f0000f55f30128018c26241050f52f0009049005501082e005a1280040011f073f01600076000f1f530f08080f0560ff211f5130f00b5095863155f780710010f0000180001092f00ff0f561f8d0006000581350690061f031f30b00152f80033f5f247861023860052111f920089f9601007505001f5f6500310f0886910b00f0008f7008370008f65601300000f1f008080f5f08520f1f0010005600ff2c01ffc00860598531f01b6670f9d90ff1f0825915535f80266015026060f5305055f0006500b60ff5015000f0550005708690f000c15602f01002220005e5209a8b6901f8852858f585200f60991004000012051ff12337f3cf00109310f690530ff605a5bf3650f50f10100883600f82658920500320020250f70257f00f00050385f3a5df0f068010f08520f00f2a13c20510060b05f80a0061bb100f0fc3d0f5e8f055f00d980f945590f0f0385383a08f0900f005180f220018802f01f10526f80780fff4f00f1f8188908070100fd1060f005f04b5000fb36b80200663f0f0ff395020c85300a18015df1070b000011002fc50058b000f04205901600020f050170000188f0f8600b0027006005001000ff528005799c105500050bf503f628000f035067ff3bf09015f0861c658492602801d1c2601111fb5961066f00f2011a6f343001650f0f4ff0bf5086071540805f500866f1af0190001546657550006648356001116416251506f01006308511701f0f98611f055921f0a21f102506581f50f557915015f97f0112f250f0400061f540025ff0f500f61f56b8100903028f0f1089117ff17f09599845b010f05f6581028800b0010050f05bd8291308004ff1004846000f120bf00f5bf0000808f060060828ff1f80008060690010006161f0a11f62c12b000ff600d00801f13f031f0003fb9f0551b085001136176802f21f182126b095f6151100060b65400891b0e005f110f1f86f055450f1488200d500f000018100450b1011ff001103062fff93f38fcf050050f00056ffc5030fed8f25010511630b100006180660013300506310500000f66f03d385d091f59952018f651181903630f007069000111101161f7611170f86066645f829312809689020050f5002908002000658105f74150f720004110d85f01220f0e0020800f1b418001f6030ffffed2f8b0f0380570f00ff01010f1178007070f0366502660f718008b10ff00230005753f580350f08000222fff9400b2f02b508510828f8002f0f0f066600850491061429a06568015031097100ff103090761264081061028005f5600118190555620000ff506135f1519001e060310404320722b10610807895ad01e002106018485f20601103f80751625f0011f770586fa686f06812327006f05fd00018f60f025055f535360f85ff1f5850500464061f11f0085f805105b90a6f52a900201d08045f550f0f72c60082500eff88f02000353648008f564000517980b301165900f0f5506ff6590685b10f091800c651790800015f0700800110030c141ff0f0ef500301f40006515f19f0568fa3000895200f30087f51f0f600055f01f080600600557005000a005900507b212055f0200045600530216110c0f88f63070208365289f0f5568f0610ff881f0f108f00f33f1f100580ffbb091f7008638f16530a054f20010f78f6070d408605005f0290008ff907701000059000d86dff12106108f04ff200f80f0f210fd69603629554698ff66b60208f05f4801c0081000560092f8f038f000f0010005055b02000880505f0b05080003f0f462035545061f505bfc0f9126f128f00b0250af0086485f9550f00810130d510ff0955116f1047f00f15f0d0f1f18f3008f0b080613f10050569155271500122b08f800610000f000050f093661500f000060fff786f0083642006bff0ff880ff000d86051f00fa10f710226f5558dcf83000505f61064f0005728013078506f8f00880800600610ff955f5ff8f5f00510f050000804011021082f001761f0550ff100000050100003a560f4500f0fd00f8c0250ed076cb02e60f088158500f8050080500040d7301f0ff0ff7f520030160784008c1f95f810580f9f0581006f060f070400505956310f019859104006053910006f950810503535104000060600f4504c35f318f2162f10f051f6890000211400d9481f00500034c1080025b0901340ff917045f05f36887f003550a50f58f601d008571f000f000d7252ff1511201100000603640255b001001660f8040f10f00f19f053869d1630095f80086f80609f10010026000080063f790fffc016105500ff11062260ff00ab1179550fff05d6f0620557102125800050580ff8d9045c980f090606008780fb0d5f50015ff231f0b6b0220f9f612106358030f0f60bf0b02a564f05865117091f5624f0023d619ffb13f2005fbf50da0f1f01ff56f0805861005000400b073d65001f98000f0061680f2581501f8f359f9505f280010004c209ff400952b7112f16b608f5515f1fe09300fff52115f030f9100ff0006f081ff8f8f00003095006950f20f08f051cc5937990535f02f05110f19f09f2ff150555575f55258f0ff00202ff05603ff0f6281081ff60000f508619201280810b5f5060f8f909180a010820805000f260f07051c368030506200161110f006059f007f7f1f00100f130f51011900050f600680f000bffd7382660051ff0002fcf610590244506f68180f60f60850689f50f20f00381ffe8f91814f002f710df47e700f0290f0207f0f05f1f0ff288221f0600101523f0f211d88f00f0058f9018f6ff10770502d70f1098110300fe630300af65091f15000f0f98f8f3006011006002e06fff270ff006110f9300618fd05060508f51155058101f098f808900001f211806ff525f00845100f00860e00111611604b050901000082018f01010f0017020001685018ee10f05080fda0605108f00180f8820200f00105580000f80f24000015210b002870000245158260061159020a005f028c12510f110e60f05f61885d6025f0f080055f10d0f1f650f620100f130615001105086d20bf0f210301666805ff00179f0b110078f12371f0f010c30f70000668ff60865a00f00f15161f020608088716086508f00503505f06ff06811655b9605f25f157d1050080b0f950187201f00f50f10f6650865f39635610d0f233435780f71f1938ff000b2f0020b00f186914a02017868600097583690766f7013006015187f7f2a0f21833500f81c5f0208320ffe060ffb0185900d72651f0f01cf4b5d559880b20000523d5004f822f809708054281810f0e01f19c301f0f0169050003935ff5707f12f60004106f5105210714ff0062050060f6261818030500f00000002c0bf2450230101000020fb060d50060f11025210b4f53a050f00111f651fc3b05ff20f500b03988f8553f2551815f009f68ff600173414b851077051989059550b0f04169500a6b0f6100a085f060505900f250d0985806450030c150000089685f05606003000215f050105f100101f56398f6300961100f003b906910f50f9338891f1362f08810058f166656ff900587f060101062f860a5900f09bf58f0100025010f0006050f308f78350009899b660f005702f0016085f0f00f5520132c5150900fb030d50520b41191005400008832f1b94010066104010020913f3638ab1e8008800300f801f76472008100e61f000006001500500001d650f100a01000021f50532ff4023ff356107b80a03ff5092912580090009f0000910150f00300065f02f0501001f105f0f1f5ff30f175920b0103050f8500b10001800085768150f91ff059501ff4004820008050f0f0b729195520060f0905f17518030012089029f87108660000050606f0818210f0841091102643e00f08f07055655f1000f10150f01df00f9210750055f30668810806506b5053f800f23700000000f00120061768f8000601f500216f3801956181416008f1ff3f5ff10012170607110b503201fc019f250f506803150060670f036560b19c403250185ff4000881c005391016003000380848000ffd036f5001f810f01f081080510482f0868f8010f0f696405f2590000095f658808280600dc9050010570120001500849f3505800fff5709e60fef060f2322f8151560f5b0b6656f391175f0000f1f21ff3000f8020009080108df01050fff0007000005ff91784ff0601ff01668f88709f5850fe65f009b0008560000e6f2056f51f5758f0f0900d90e000f02460058661fc8860f11700105000600555111b002080b8098613010f80595905f18bb5f2005f00558f806620f83461f0286561005800084086135f000000821156d5031080000f11001068e55155010108f865570f851864401088051bf56253f435604b8606080115979805fd850f886f8f46fdf0108600fd98402036f5130f8fd3503008502f007d650500100f1580000015d55802125686f400655135059289393400855055508305000205f5401515f249803900606809052b80100111195f0f055f00006600f20500000df8016f0030080010005808f009515f0f2c8006f965f1ff0009000f5f1000005a070715031178f605f008b0588f1f78d00300c200e055515848f2450111650b000f50020103828200f00f008f60f8050051000f68400ff8609f065b020380f0216900382b00f8f84f09896f5f0f80ff750690a4f0f0ff0f0f0b59012606120168fff0100df004558f1f1b30f300586f00f60506f9050050f09ff108c85f03650381f500318581ff7193005f500080ff053b10950106fe6070f0f00013f86898000f908793f8025012f7d0f00d0d8100510f9920257f800f3006f4006f5100bf65003807100f0cfaf000f6630f012b2f0096701f60f008f10014006fb10007849f8010b01ff0820f616f2df0002301000060000600516f38768f7503f00510060f31051f9ff18a59032f031f30051000fff50f5b8100f605f636b6f0f508000820b6090817f0f610004fe5f4312f01019ff3801f0000182200f0558fff009315c014f1513f0550800102008f5156006056860729f01350f116f10087f00035780f51f8610500008f700058f6f292f0005f189590671298b581308109f088f056551030055811671000001506fff08693f6603508055f066565d0f3205613f4f0961a00110500615a102b2f0f00d15a5051f76510550b800f005f005100402808130197f11201686f810000961f5021206d0f2ef55f6ff61805007158aff0321f5b050f950280f0004f00835f511b0d500b111000f80f20020f873497009160200f8008a2005f7587ff0b0d81707170f41017ff20580507830966f1030ef60580100026f6041507f7005506510fa3cba808f120149819000ff26801001f5000000f01ff0008006058045f0f0f002307507000f1b8f386541555370158d03f00100152f05f8f8a1f905f190208f26088f10800211ff19ff377f90600010516089500522f080f0358f0ff805f270f71f3ff50008f15070175f33ad806f0f5700f5002f38009050f150c0f00ff000f1501600800f5ff0503006b8400f001f50550f10203f5d3f35005b05584f500ff052005f0fc09f90f0d58726206011f01000528060f90b1000010000000f176501822898f04b0f00f19168060f158500ff13f50f000000f0022100705265151f1ff0302f00013020375b5638118fff0016000621f373f00f00500f5f01f91100d95ff80511fff508662800800210358762be910f60018a9432070f86f7001c1f0020508696080110f0418a70160008be1558600204005f0802508940f81610000500300f854f201fc40f80a1d155070708f1001182658500b5852506f05861001008f0ff800005800d0012005716101000f018188940f00f1b6f50000c03853809295eb6f30f0004fdf002001f0f36003106123220f048025905500d150700f0016f04688501503011ff81f580900630fc51bd605fd203665295b588670ff0390f0601f00d90c019f850f6f632c000050031031311665880f0000505803f0f1f020f80f0730f604a006200f05f80018500f8580f20a60053f05b0f925102d00f6fb0d800f060f8600527f175110ffd909405000050f8f600002bf56568004b50088f00f019f690003dff959f102650690a0919b03851403a158f1901f5f815058f6f0000f3000072011105a80800046080f0028f8026f06282505ff716111390308a0f1601f3785f880f58900200830f11010f409805f0620053f00f30ff0d0f50f06112f00001890802350dff6f208028954566860a03f10f0118140083f22f09f10f7500f14bb5059510f400f0f83603ff56f1f003020055217f1830bc0318b0fb18afff00590050f005581062fb6d5a00030350f33100800010009076681501f8680700381518059700f01080478068f6008030f052302f010000b05998f550811011108e9f008505112f0f164f1f000d010f206b1905f980de008c0f20ff8f080600853011007f5015f6146cf0206e66800f0f7a52d088870080d6620860561318700ff1f450820015f0310f5580006f0f10311580fef0f0613f00f90404015050018520f20110087002366690156151f085c402006005065000f07085e9f0000680532600100818bf8560b0780f07df11f096100f40061110021f60f00f00557087710170801800190016b09041e87650870198183069f68161001f010f60ff9101d3d001260164006088f0fd385f020201500f3f61bf00031053710001f0de3f526419078608081f8095250650120007df06f51962160f4f602b651818a101f012080f0f0f31f3f125408045900618061f0f9f80404195000f01960060157fb086019f111ffe0100f4100f110f5887167068f0f001f02580228186061101f2a021cf02000f5fa50008060290712066008f270300b8d30f0ffbff22000f641c10a62f0f5d0f361f55041300840f68f55961018800090600f52282a2f108165f28ff0af00f02801830f6818f009328606f9f990011128080645ff4002003f051cf6f003f00105f60f0521204ff1f11f1f0e1d9f0100005f0f0ff955096fff0001df50f01f0c11f1df0800ef060556506b0530560f035f01503088052f68000f218505000c0000005f960160e081801ff587350f500d1900f0102500306ff9512080100ff1f50fff25f560f611804504f50006f8c1f151f030f6d60f8060762081005602c004f050e980f6f03a5fd0ff6260160f7cf59b08f016b350079260f093ff63055213006200a07f09eb35998c5500a8209608f2f88040655b0017911105203f008050ff5000000f85209280600035f15f11004f0f8025e10695f00600df210500f0f88070007ff5ff0207601000086f58051f280300055f50ff8f050e041f5f0d0809006c50800600255050610019172f20161152f119805580009038510f4005ff00118546010101f0f0050080340051315000fe5f02030630560ff5111050f1205f0d1202720002a69f010389750240000d1111051836310bf00038f056f0f4520804072705f450b05f008fff09f01f98ff20ff0000f00f6005f5601007f35808605e8603c006098d86200000f015b8d0255115f01d150b80089b0b72103f7f90bf5ff0800f6481650a8f089f3022f0f10907811002bf8a33020000501081110cd10d0f08009565ff081660588000480f605b0102161489000ff0960f6065f89680550a635c1051632100406a20c0608f0280000000f50598209f00fb0f0f3f1006109955f6800615000060011f5940ff3fffff011008000f120af8801f198266f11600810f1fbd00f131098a00710c19fa000f00f06105b230f0ff9f8529430080f7047560a8fa70f8ff60f15f9fad6f805f3c600052a5b00f6500400549025e452d050950fd500d750670513f501f165b05f03003562f809750000ff02f251509000051f1f10558bf50531d00390588f0f566b8015d3ff6510f16f1842110f5f5f218049095060566d8f25586f390f9f057b4501b000033000b0161f5af501612f9634f041f80fff7500f0050f0211709f061fff0dbf10f17005f5200500f2506690501480010f605800f08505806101f6ff508f159946050068f608d6fd3f063c7fa8150f88ff0ff11092b091508100005150506ff10f0f00088907002310b897002588861000008610385f090306750f080169f0062f3d661000026010240f0011060011f0122f8f3d52f11100d91a053fa95100809083b6b0480853563020b8016f2505060000151530010160870f60f10f00910f5300079f1510812745651480790681008086000bf522fc08f15900f740815f180f855f899bf6f189630f0f6f095a50100c1200f681f084168007008120068015b093082785f4384632588600df12f1512b525525df00050103f067a85201162002f97f0653bf8858c005f57b090f80000920b09f709f0f18012007f50f58f9069f6f61801ff20016f6e3587800535c0300f680f6b8232f60070676c100019090328005fe060f83ff50690629c0bff51805b81f90fff070000008f0101f0038d01bf0f59b0f0900861f180004616f06f0e65033806fff056117f0f8f0000000f0800f463f7005f456008510506b090098110050d5540bf20f0000167f63500001900f0010511075b605f7f6100059006810006f9f2b65f05906180005fa7015ff5108006009164f704680f03006716f7b90111f8012600bf809056d03f00a0518c70b828000f805813000286bf0000c018d500f550900ff24ff8f50f0640f3008f01500005f15f056f00";

    public static final String ABI = "[{\"constant\":false,\"inputs\":[{\"name\":\"from_account\",\"type\":\"string\"},{\"name\":\"to_account\",\"type\":\"string\"},{\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"transfer\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"account\",\"type\":\"string\"},{\"name\":\"asset_value\",\"type\":\"uint256\"}],\"name\":\"register\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"account\",\"type\":\"string\"}],\"name\":\"select\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"},{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"ret\",\"type\":\"int256\"},{\"indexed\":true,\"name\":\"account\",\"type\":\"string\"},{\"indexed\":true,\"name\":\"asset_value\",\"type\":\"uint256\"}],\"name\":\"RegisterEvent\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"ret\",\"type\":\"int256\"},{\"indexed\":true,\"name\":\"from_account\",\"type\":\"string\"},{\"indexed\":true,\"name\":\"to_account\",\"type\":\"string\"},{\"indexed\":true,\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"TransferEvent\",\"type\":\"event\"}]";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_REGISTER = "register";

    public static final String FUNC_SELECT = "select";

    public static final Event REGISTEREVENT_EVENT = new Event("RegisterEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Utf8String>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event TRANSFEREVENT_EVENT = new Event("TransferEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Utf8String>(true) {}, new TypeReference<Utf8String>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    @Deprecated
    protected Asset(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Asset(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Asset(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Asset(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> transfer(String from_account, String to_account, BigInteger amount) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(from_account), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(to_account), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void transfer(String from_account, String to_account, BigInteger amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(from_account), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(to_account), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String transferSeq(String from_account, String to_account, BigInteger amount) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(from_account), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(to_account), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> register(String account, BigInteger asset_value) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(account), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(asset_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void register(String account, BigInteger asset_value, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(account), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(asset_value)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String registerSeq(String account, BigInteger asset_value) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(account), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(asset_value)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<Tuple2<BigInteger, BigInteger>> select(String account) {
        final Function function = new Function(FUNC_SELECT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple2<BigInteger, BigInteger>>(
                new Callable<Tuple2<BigInteger, BigInteger>>() {
                    @Override
                    public Tuple2<BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public List<RegisterEventEventResponse> getRegisterEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REGISTEREVENT_EVENT, transactionReceipt);
        ArrayList<RegisterEventEventResponse> responses = new ArrayList<RegisterEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RegisterEventEventResponse typedResponse = new RegisterEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.asset_value = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.ret = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerRegisterEventEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(REGISTEREVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerRegisterEventEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(REGISTEREVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<TransferEventEventResponse> getTransferEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFEREVENT_EVENT, transactionReceipt);
        ArrayList<TransferEventEventResponse> responses = new ArrayList<TransferEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventEventResponse typedResponse = new TransferEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from_account = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to_account = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.ret = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerTransferEventEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(TRANSFEREVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerTransferEventEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(TRANSFEREVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static Asset load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Asset(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Asset load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Asset(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Asset load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Asset(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Asset load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Asset(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Asset> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Asset.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Asset> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Asset.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Asset> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Asset.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Asset> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Asset.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class RegisterEventEventResponse {
        public Log log;

        public byte[] account;

        public BigInteger asset_value;

        public BigInteger ret;
    }

    public static class TransferEventEventResponse {
        public Log log;

        public byte[] from_account;

        public byte[] to_account;

        public BigInteger amount;

        public BigInteger ret;
    }
}
