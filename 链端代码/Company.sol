pragma solidity ^0.4.21;
contract ReceipyInterface{
    function createReceipyAccount(string cname,address cadd,uint initMoney,uint ctype) public;
    function setNumber(uint _number)public  ;
    function logAllReceipy(address _add) public ;
    function CreateReceipy(address _from, address _to, uint _amount) public returns (bool ok);
    function transferReceipy(address _from, address _to, address _newTo, uint _amount) public returns (bool ok);
    function receipy2Money(address _from, address _to, uint _amount) public returns(bool ok);
    function receipy2BankMoney(address _from, address _to , uint _amount)public returns (bool ok);
}

contract BankInterface{
    function createBankAccount(string cname,address cadd,uint initMoney,uint ctype) public returns (bool ok);
    function judgeIfBelieve (address cadd) public returns (bool ok);
    function moneyIncrease(address _add, uint _money)public returns (bool ok);
    function moneyDecrease(address _add, uint _money)public returns (bool ok);
    function isValidAccount(address _add) public returns (bool ok);
    function searchMoney(address _add) public returns (uint money);
}

contract Company3 {
    address public bankInterfecaeAddr;
    address public bankAddr;
    address public receAddr;
    string public name;
    uint public money;
    BankInterface bankinterface;
    ReceipyInterface rinterface;

    constructor() {
        bankInterfecaeAddr = 0x364c33ff2c488e520d9f6384f76135ecb06aab35;
        bankAddr = 0xbf75837302328e53e3f4eac8334d1688f1c33a5b;
        receAddr = 0x86237d997b6cbce2291d2aa16a4c16284bfaa817;
        bankinterface = BankInterface(bankInterfecaeAddr);
        rinterface = ReceipyInterface(receAddr);
    }
    
    
    function creatReceipy(address _from,address _to, uint _amount) public  {
        rinterface.CreateReceipy(_from, _to, _amount);
    }
    function moneyDecrease(address _userAdd,uint _money)public returns (bool ok){
        return bankinterface.moneyDecrease(_userAdd, _money);
    }
    function AskMoneyFromReceipy(address _fromUserAddr, address _to,uint _amount){
        rinterface.receipy2Money(_fromUserAddr, _to, _amount );
    }
    
    function logAllReceipy(address _userAdd) public{
       rinterface.logAllReceipy(_userAdd);
    }
    function CreateBankAccount(string _companyName,address _userAdd, uint _money) public returns(bool ok){   
        return bankinterface.createBankAccount(_companyName , _userAdd,_money, 1);
    }
    
    function moneyIncrease(address _userAdd,uint _money)public returns(bool ok){
        return bankinterface.moneyIncrease(_userAdd, _money);
    }
    function getMoney(address _add) public{
        bankinterface.searchMoney(_add);
    }
    function transferReceipy(address _fromUserAddr, address _to,address _toUserAddr, uint _amount){
        rinterface.transferReceipy(_fromUserAddr, _to, _toUserAddr,_amount );
    }
    
    function AskMoneyFromBank(address _fromUserAddr, address _to, uint _amount){
        rinterface.receipy2BankMoney(_fromUserAddr, _to,_amount );
    }
    
}