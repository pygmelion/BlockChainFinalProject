package org.fisco.bcos.asset.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
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
public class Company extends Contract {
    public static String BINARY = "b017f36082ff82f0030750561100f521905fffff615f00a078920f2f038505ff05f5f1f06dff5f1f06f04f380f81ff9fffa6ff9b000080080f550f7ff6f01f00fdf85100f69f5fff0f8ff9435f1090f40b9ff50f6f503610d8f108f218f60ff0cf6070f876f7f05f1f01f0100990ffba20011f0f2148f8f03ff05ff5f243fb15034f30faf195ff0f42f860ff9f58fff606168fff0610543f8f5671940bf06f6f0666f067f1f0f0c0f60605f0fff7fff06dff4f4f9f0fc1f06ffb208fff0ff60fff5bffff1ff0fff0502fff09f50ff860508611082000683af00f857950438010000d0300faff5f5f7f0fff0b7ff8f0009f650ff9ff920f6801f0516ffb015300082ff5f0100005f0646f78ff5ff09ef11000c50bf40f006f44ff25800ff2f002110880851ff0890403ff051ff1ff1ff5f5f90f201ff67fff0f40f5ff09f30f2fffff8600ff0ff160f05da08100006ffff6dff51ff1ff63f268f892f0f89ffff3a0ff00320fff12ff922b10a48695a0f5f7d0f0f8f881ff780195f56f06fc0f2f110f84f61f56f0001f3ffff1082ff6f000ff5008fd006f0ef009f0820ff621398fff08ffff2f1452ff18f1fff0fff60510ff0030f5f1ff8f3530606f0f06f16ff8f010f53f08f6f2424f05152ff6908060610303ff00f38d0080f1ff018f0818ff0f6f2f00ff0898f1f85114f440069f0008817f29ff36625516f8018f500f0196f8f8f05f1f856ff50001ff0ff00f035ef0f49f01f00ff20f131fe169af70f306ff03f8ff07f5ffffffff9f6bf5fbfa8f15ff5f0bf05179f80ff0f650af005ff0f00ff0f503728bfff0f0010ff60cf20f0006f052f01701760f1005f0f0ff0ff00f18116b02718f3f2ff1f092f0706808096fff08604f532f0ff800ff03ff01f0fff6f028f5ffffff00fff68183fff650030ff080f0f16ff20749ff30ff0000d9f00fff4f0f0f8f1006f01f200f530f501051ef0f0065008f00f4f0980f9050390f32f0ff00558fb3f6d5100f0f02760f00398f00100f9574f15ff09500f05150ff508f030f0f21885fffa2f30fff06ff50271360f0f0f7f5f68ff02064f8f800f65068ff008f51500ff535ff0d228180151004606ffff1f0060f0d13ffbf13f30fff6046f2565100058ff19490f150ffffffff270f51ff51ff3f80f616f80f268f5f5300603f00858728f0fe068007fff60502af11f10ffff00812516096657bff6ff00030f01f1f027fff110f5f1456f11f099f9a90ff5ff0f41810f5ff0f35ff809f61f05008214f1f01894200010ff10a90610e8f08f119fff49f102f15010ff5ff6586fe0f0890ffa30f0ff1f0e5681f6df1fff6f8f7ff900559f60b08f65f006ff7f90f65ff94ff00f10125d679350ff16f0600f0fff0ff06251bf4f7601201f0f00031ff40f0ff1f053601f00190ffff561f50ff9f4f8f60f51ff086f001f03550f39ffff00f0f0f30657f0f3016105a05f5f5f4005f0ff0f400003f0b0130f70fd100585f90dbff10f093f164f58ff08fff90f1f85480060560fdf58c00f9ff0f00fff891f632f480007688f9f00f0f01f0f1bffff16ff080f000b710f0fff0f057062f01f8ff4ffff0f0ff5ff01ef00606f063fff0f0f10f00f13483006f9ffff0901f3ff0ff60fff06f1f6ff06f0f8701fa907f55f3f0565260195011ff9effbff0ff3508b406f06f20316800b60760390bf0f5f515fff5f104f0f98f008f029f10f5f0ff024f0f1062fd16ff61a1050119fffb0ff438950808f13f101f00faf1052d0f35fff10f95f14818000f1ff0f2ff6f0ff9ff0f1ff089ffa0f3fd6ff261ff2ffff0ff0108f866fff1ff77f8000f51dfa0df850508ff00f6f760f0182ffffbf03050fa7fff60b0fff0fff61ff3f5f86012a8f710ff5f005f190f00060f6fff54ff500cffffff340126ff915510ff1301ff26f05f08000f76ff13f3ffffd3032590094203fff590f538f60f2090d06d9f2f5010ff0f505f058b5070f52f208fb00925f1f5ff02ff1506f05030f0ff8b388000fff3fff3010000060521ff20f53550ff05608002261fdbf00061400f51f0f50216ff50500f1f08f08f62f506fff5f0f2f090ff0f1501081f6140ff11b00ff00f6f445ff7008f633f1f25ff0f00f80560017fff10f069fffd9ff42ff0ff11ff8ff60f5b0ff000174f5ff9f093f407fff0f015011805f60f600f8701fa0f5f93ffff05ff0766f0f5f0050f2400f0fffba713f83f8104ff5100f55f3f61f02400f0f180100f1f8fb11f5f1501f0ff3fff3f651ff6f50f160fef0f5f00ae60301fff0600fff8ff0f641f011f7fff9055fffe917f265ff006f8000160081f65f5628f07fa41f8f000500fffff0fff00f400f07f00188fff9f28f0518c40ffff14f07fff79f00f00ff1f0ff4f40fff2f0f0f20f016008ad0ff5f03687b5dff082f0f760065912f98e9f5ff01fff55ff035ff3ff01f0f8f8ff58ff1f2c71ff900f1f67f1100400055f86045f00120f80f6ff000e38ff39200d61f005001f0f09f5004ff05f1ffff62ff568ff90eb0f357f0f360fff4f0ff6074f5ff5f051f0fff6fff5f1120fff000ff8ff8f9806881f026ff3f8770fffff4162fa0003060ff003513380f762ff5188f0162923990ff055500f8601f0f0fff0000f15560f5f8ff5020bf10e2f00f1f60dfffa2f300f0f695ff7f700860d10005ffff8100f181f508f0ff900f36700f2f0fff0f89f9f5ff6f60f8b0f2f89ffff34f0f00f5f00f01a0f5f1003ff10f9ff50ff0ff0f05f706f2f68004ff5ffffab0ff00ff00ff00f1030ff0df170ff9fc5f8ff4100f15f80f0092ffffffb1f5f5fff9ff5f017f05ff99f0f5fff5f003083f021007160f50ff7ff0f6f53f50f64fff5080f38f0ff801ff94ff93f0f8781ff82fe0f54fb606800f03850f017fffff736f1f0001f6f1ff30b8352ff6f0ff2f4bff0f2650f5ff340ff18f50ff5f015ff081053f0f7f7f598008589600fe9f01f1dff2fffea90500f806f22695f0f2f90f893f16f36f8a100f020582fc0f75885f581f63885ff001f65bff00f9a67f0614f1658f00f001000fb20f0186880f2f00ff4005f08ff6fff7318f90bf5f6ffff01650f3dff900ff106009ff4f60b588f6000ff00f110900f133047315df40f0151b03fff2f241b6f10f0ff53f08ffff010b5f050ff0f1006ff1ff09000ff0520f660160ad515f33ff0ff0ff0360f0ffb5f000f5ff50f10f53ffff5fff2ff0f055fff0f010160854fff0009fd70af0f0f04fff6f0001f3f13d0f0900f012f8f000ff5f259fb00fff906ff1fd50031770166f00d307ff0150f5f1929f2ff6fd01260fff8158f0f2051610ff062016f03f20fff3070001090f03ff05f47eff0ffffcf080186ff00f0ff0fff61ff60b0f666f78f301fdf609f1f5feff9f11fff1165f01768ff010f015fff1a1fff98f3af3f0871f0008dff00560f54f5b0faff563f800f11f1515f1f60550f0901fff71058f30ff1008f10683f3f2f6f900f0ff0c11f7f0f3f4615f0ffa48f559ff91fff1956f40f00f05f0f800f50f0bff0f40346503018ff01ff06fff696fff07ff5ffa6f1ff022ff000055f623f00f0616ff244360f51fe3f10f803555ff7ff16f170a126f6f5f0a0f5051fd40f0100f6dff709f63505ff0f1f6f517f1f8f06f5fb30f00f9fd1f101b00f0f12fffff1f0f03f80ffff1606f102f88ff0ff8ff091060b903ff0081ff00606f50f51f6f006f8525f5f11fff2006090f0050f5ff05f3f0f0f1f306ff0110ff0026f8ffff0001516151ff556f00f15f0ff00f100ff3461ff0060f61115001f58ff01f023b00f3000ff0ff80ff110955060f6f16f10fff50fff0f081f5fdf16ff1905ff620551f581f0ff6f67fffff511f773f354ff09f20591600fb10039fff481ff1230020f06180ff5300f10005f50fb600f010f0f8000ff0faf08ff4f3f8f2212f305ff140f00ff05fff0603611f04b61ff5fd10fff1b6ffff35671f5fb10f3f0f8f58e0f00a85f060f0f61ff5ff995400002fdf003f7fff840f35d54ff9f55ff2582550ff1f12108f06f2fff5f190ff7ff00f14000ff61ff5651016005f059f306165ff54081f2616fb08f701f0f000804844f0f9708ff46f05853925ff1f0f39ff100f50f0f0f01f021000000fd7f0538401f7180fff0114ff0508f860f838f00090ff058f15f06f0ff11216ff2ff600019ff55f60f3f162f0101899fbf1ff0625f1f623f6bfff60f00f430ff660f081f6f1c0f13fffff01510f6fffdf00f00f51b10000f5000900ff18f3f3ff938ff85ff560feafaff8021f216368f3ff0551f08fff248fd07f7a006ff8f0f016f017506f500501f4fffff791000f89f3057b549ff0f0160f008fc1f06f40ea00200000008df9f90f25f2f0b1f0b171b0ff01ff0f6fffff0f50ff1ff0575fff5073f0002f0f080b0641f5059f4f103ff0f69ff08fff10fd0f0f5fc612f1f60f006620860f8f93f0f60ff303f05062ff1f20017f90f601ff630f1f0b10659bff0725f6f01005f13ff0f03f3fff13505f1ff128f2090f6555ff30080f31000ff9590ff085f0f00d6f0fd9eff5ff0f0f195106f00f0257ffff0566f50fff80660555e0f073ff2cdfff2058f71fff2f15f161a60f0005f1f9040f3f138b024908019ff01ffff5af0f6ff70f000963f50945ff50065aff00005f363025f1f8ff0f06898ff5f0ff40ff4f0ffff65ff000fff60f2819f9f0fc5f76a04a6418f9b090f9ffffff3003f00f09f008076ff302f0f40fb36ff70068f150ffff2f57ff0fffff1f01f29f400000f0f3fbf8fd4f5fff5fff65f0050f00f00ff608f4001f631045800ff5f000ff30ff80f0f003f0f0f15071fff5460ff3603ff01f0f65ff6fff653fffc9041ff93f4bfcf2f0b01f1f10ff258df5d096020eff5608ff005b3f0ff614f208f1ffcf7570606f65f39ffc0f0060fff039f5faff41ff29fff200ff9d00ffff030500ff04051510f808fcf0f165f50f0fff060735f8c13f98f02000f59008ff050f0ff40001516fff1f505f4f6f10d908f040ff061f0f000ff0f0f0f887ff00f60f0fff1f010f050360f0f125f071900f0f8f5905f0fff1f809f0f55fffff09092bf14509100df60f9010784f0f7f1b9ff8fffffdf4196f9605105f1609f6056fff1b5bf985f5ff1f5103b0004666201055869ffff01f0f5fff9f5f2156645faf66ff6a0231182f0ff6fc0f62696f10000f656f29ff2860ff00f04ff43611101600ff000ffdf40f0f11f08f7cff065116f5fff19f30f0f0568805f1070f1006ff656500ff0ff1ffff0d40e00ffff0ff0f330951058ff73ff8ff6af1b0f5060ff1f00ff9f0ff35f008f6f5968f50f1f06fdfffc0f06f9076fff486f0ffff50907ff90ff150ff0e94fff0503ffff981010ff0ff707f8f93ff5061065c51f285a0f810ff60ff60a05fff00718f06f02066fa0040f96951f786ff63903110086ff013f1f25f9ff57f5f0f8f001f0f0306ff165f08f059ffff2320f006535ff00d6690f1620f50f953ff5f267f9000051f8f16f10ff7ffffff2ff2bf00008ff0002708f1ff8f855ff650f60ff00ff0595f630f0f5802ff0f004168f8100f0fff159f6fd10f4fd688f25f0ba1f6b8ff098f53202652502ffa100063820f90925f0f8f0fffc25f9b50f3f6d8f0ff5ff0ff0f3719ff86f5f5589fff02f5ff60c9c606f58015f10f01ff0f01fff77f30518ffff707ff39188ff8f206f0f60780f67ff8200520f90b0f61f08a8506ff5f00317620fffd3f685f90149ff50d0f108165ff5f2f07f07f0f9f0f00f6105f5f18410010001f5e56005f92f00f0f9f11f7f610f090520f8f06f079005ff3fff667f616fff0050f01008ff000f0f2ffff0f50fff060f4f0f0ff11ffff26ff0fd00ff3ffd0f3400004f00ffb6076d38f30ef80f8f3bf00f2f6ff6f5ff2f030f05b13582000ff33580df60b01fff30ff616ff0f5c52f500f0111ff00fbfff90f069f009006000f6ff006f85f80000005188fff3f4105f1ff0f1f9f26ff5f01ff17fff00041ff9f6ff0fd1f0ff0160050ff31601f0f82f0ff9b856ffafff965f00f172f94560ff29f5f15f3ff01ff0ffaf9529364ffff051f2f86f0f5f0ff90305bf3156000ff1f68050f9f0506f0000000f610d10520fdf300ff900000f9afb7106f80f1f6fff030664f65f4660631710ff901ffff03ffe81b16fffffb624400620810f3093539250b59f646fffd0ff5f2ff2f6f65f52200100000f0ffff0f28006f3f03faf50f100ff00310340fffff00302958106f10517360fdbffe006f0302784f21f639f5f26f60fb10019ba10f0f86f95ff210ff0d625900f65fd9f2f0ff980ffff56f0218ff658014000f855ff0f4f6f20436ffff5f16f0550f000f196f05170f5ff05f1ebf6f0f01005f84861005f01ff5118f00fb51201f0fff0f9f9f601ff5f573f122afe16f6bf5f3f55d16f7ffff61f139ff07ff9ff46f8075000153ff081f58f6ff0b80f8050ff036536520f0f0166509185070005f817f5ff08f1f0b0886f011df100ff48af00f9f105f7f0ff90f1ff0f000f0302005011500f3f81f0f75b067ff0ff985f9f651563a5f61f40000f16f917ff48ff10f0ff8f6379f710158ff56080ff5b001ff2f16f1f5ff6ff1c0ff5f71ff360100f8f0f1ff1fc8f0f500faf5180ff53dc00700f1f36f3b531018bf56073850500ff573f07400f580a1fff1f6015fc588102065f9f70665f000f0f093f02f08e0ff06f6f80600b019533090009ff0f3661f90ffa0105740360ff230bf0dfc5ff5b101183ff0f50f09b09d568f02a0ff6812fff17f0bff953ff0f5024336ffff8600f0f06b150f010fff1ff9ff4f000098ff0ff6ff681316ff14f810f0595ffbf100f001d5800a40f0f12f020675df0008f6500f64912f0010f0be5fd18858ff3178f00ff060ff1ff806086ff8b4bff1208ff02f0001030f086f95f1ff1f0f10ff0f04f0900f3f1ff0450f300f0613ffff5145f00f09ff151ff0d0900002f6f5fd0bff10550fff16fff0f08ff83f5f638f5d080f40f8f19161f10909cfd60f60f5ff0827f4f79072f6ff80788f369050f09ffffff0f809ffffff6503ff04f09b6f50ffffb1f0f31f4ffc05f85f00083fff116fe50ffb0ff0f069ff100fff0f6f0509f00f0f01915f4aff3106f98061f20f000f1fb8f25f9f81ff807f17f3050f8310f0ff0b00f882f3108ff06f82ff0b1ffb60f060fbf020065f0f198032bf00265f16f0f060f001f05fff5257fff0cf0ff";

    public static final String ABI = "[{\"constant\":true,\"inputs\":[],\"name\":\"name\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"receAddr\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_from\",\"type\":\"address\"},{\"name\":\"_to\",\"type\":\"address\"},{\"name\":\"_amount\",\"type\":\"uint256\"}],\"name\":\"creatReceipy\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"bankAddr\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"money\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_fromUserAddr\",\"type\":\"address\"},{\"name\":\"_to\",\"type\":\"address\"},{\"name\":\"_amount\",\"type\":\"uint256\"}],\"name\":\"AskMoneyFromBank\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_userAdd\",\"type\":\"address\"},{\"name\":\"_money\",\"type\":\"uint256\"}],\"name\":\"moneyIncrease\",\"outputs\":[{\"name\":\"ok\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_userAdd\",\"type\":\"address\"},{\"name\":\"_money\",\"type\":\"uint256\"}],\"name\":\"moneyDecrease\",\"outputs\":[{\"name\":\"ok\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_userAdd\",\"type\":\"address\"}],\"name\":\"logAllReceipy\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_fromUserAddr\",\"type\":\"address\"},{\"name\":\"_to\",\"type\":\"address\"},{\"name\":\"_toUserAddr\",\"type\":\"address\"},{\"name\":\"_amount\",\"type\":\"uint256\"}],\"name\":\"transferReceipy\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_add\",\"type\":\"address\"}],\"name\":\"getMoney\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_companyName\",\"type\":\"string\"},{\"name\":\"_userAdd\",\"type\":\"address\"},{\"name\":\"_money\",\"type\":\"uint256\"}],\"name\":\"CreateBankAccount\",\"outputs\":[{\"name\":\"ok\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"bankInterfecaeAddr\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_fromUserAddr\",\"type\":\"address\"},{\"name\":\"_to\",\"type\":\"address\"},{\"name\":\"_amount\",\"type\":\"uint256\"}],\"name\":\"AskMoneyFromReceipy\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_RECEADDR = "receAddr";

    public static final String FUNC_CREATRECEIPY = "creatReceipy";

    public static final String FUNC_BANKADDR = "bankAddr";

    public static final String FUNC_MONEY = "money";

    public static final String FUNC_ASKMONEYFROMBANK = "AskMoneyFromBank";

    public static final String FUNC_MONEYINCREASE = "moneyIncrease";

    public static final String FUNC_MONEYDECREASE = "moneyDecrease";

    public static final String FUNC_LOGALLRECEIPY = "logAllReceipy";

    public static final String FUNC_TRANSFERRECEIPY = "transferReceipy";

    public static final String FUNC_GETMONEY = "getMoney";

    public static final String FUNC_CREATEBANKACCOUNT = "CreateBankAccount";

    public static final String FUNC_BANKINTERFECAEADDR = "bankInterfecaeAddr";

    public static final String FUNC_ASKMONEYFROMRECEIPY = "AskMoneyFromReceipy";

    @Deprecated
    protected Company(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Company(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Company(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Company(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> receAddr() {
        final Function function = new Function(FUNC_RECEADDR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> creatReceipy(String _from, String _to, BigInteger _amount) {
        final Function function = new Function(
                FUNC_CREATRECEIPY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_from), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void creatReceipy(String _from, String _to, BigInteger _amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CREATRECEIPY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_from), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String creatReceipySeq(String _from, String _to, BigInteger _amount) {
        final Function function = new Function(
                FUNC_CREATRECEIPY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_from), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<String> bankAddr() {
        final Function function = new Function(FUNC_BANKADDR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> money() {
        final Function function = new Function(FUNC_MONEY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> AskMoneyFromBank(String _fromUserAddr, String _to, BigInteger _amount) {
        final Function function = new Function(
                FUNC_ASKMONEYFROMBANK, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_fromUserAddr), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void AskMoneyFromBank(String _fromUserAddr, String _to, BigInteger _amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_ASKMONEYFROMBANK, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_fromUserAddr), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String AskMoneyFromBankSeq(String _fromUserAddr, String _to, BigInteger _amount) {
        final Function function = new Function(
                FUNC_ASKMONEYFROMBANK, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_fromUserAddr), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> moneyIncrease(String _userAdd, BigInteger _money) {
        final Function function = new Function(
                FUNC_MONEYINCREASE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_userAdd), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_money)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void moneyIncrease(String _userAdd, BigInteger _money, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_MONEYINCREASE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_userAdd), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_money)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String moneyIncreaseSeq(String _userAdd, BigInteger _money) {
        final Function function = new Function(
                FUNC_MONEYINCREASE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_userAdd), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_money)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> moneyDecrease(String _userAdd, BigInteger _money) {
        final Function function = new Function(
                FUNC_MONEYDECREASE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_userAdd), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_money)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void moneyDecrease(String _userAdd, BigInteger _money, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_MONEYDECREASE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_userAdd), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_money)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String moneyDecreaseSeq(String _userAdd, BigInteger _money) {
        final Function function = new Function(
                FUNC_MONEYDECREASE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_userAdd), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_money)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> logAllReceipy(String _userAdd) {
        final Function function = new Function(
                FUNC_LOGALLRECEIPY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_userAdd)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void logAllReceipy(String _userAdd, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_LOGALLRECEIPY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_userAdd)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String logAllReceipySeq(String _userAdd) {
        final Function function = new Function(
                FUNC_LOGALLRECEIPY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_userAdd)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> transferReceipy(String _fromUserAddr, String _to, String _toUserAddr, BigInteger _amount) {
        final Function function = new Function(
                FUNC_TRANSFERRECEIPY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_fromUserAddr), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_toUserAddr), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void transferReceipy(String _fromUserAddr, String _to, String _toUserAddr, BigInteger _amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_TRANSFERRECEIPY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_fromUserAddr), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_toUserAddr), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String transferReceipySeq(String _fromUserAddr, String _to, String _toUserAddr, BigInteger _amount) {
        final Function function = new Function(
                FUNC_TRANSFERRECEIPY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_fromUserAddr), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_toUserAddr), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> getMoney(String _add) {
        final Function function = new Function(
                FUNC_GETMONEY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_add)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void getMoney(String _add, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_GETMONEY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_add)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getMoneySeq(String _add) {
        final Function function = new Function(
                FUNC_GETMONEY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_add)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<TransactionReceipt> CreateBankAccount(String _companyName, String _userAdd, BigInteger _money) {
        final Function function = new Function(
                FUNC_CREATEBANKACCOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_companyName), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_userAdd), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_money)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void CreateBankAccount(String _companyName, String _userAdd, BigInteger _money, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_CREATEBANKACCOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_companyName), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_userAdd), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_money)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String CreateBankAccountSeq(String _companyName, String _userAdd, BigInteger _money) {
        final Function function = new Function(
                FUNC_CREATEBANKACCOUNT, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_companyName), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_userAdd), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_money)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<String> bankInterfecaeAddr() {
        final Function function = new Function(FUNC_BANKINTERFECAEADDR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> AskMoneyFromReceipy(String _fromUserAddr, String _to, BigInteger _amount) {
        final Function function = new Function(
                FUNC_ASKMONEYFROMRECEIPY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_fromUserAddr), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void AskMoneyFromReceipy(String _fromUserAddr, String _to, BigInteger _amount, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_ASKMONEYFROMRECEIPY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_fromUserAddr), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String AskMoneyFromReceipySeq(String _fromUserAddr, String _to, BigInteger _amount) {
        final Function function = new Function(
                FUNC_ASKMONEYFROMRECEIPY, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_fromUserAddr), 
                new org.fisco.bcos.web3j.abi.datatypes.Address(_to), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    @Deprecated
    public static Company load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Company(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Company load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Company(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Company load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Company(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Company load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Company(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Company> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Company.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Company> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Company.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Company> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Company.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Company> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Company.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
