pragma solidity ^0.4.21;

contract Bank{
    struct Company{
        address companyAdd;
        string companyName;
        uint companyType; //1 is company 0 is bank
        uint companyMoney;
    }
    
    mapping(address => Company) add2company;
    uint bankMoney;
    uint believeThreshold;
    
    constructor(uint BankOriMoney, uint _believeThreshold){
        bankMoney = BankOriMoney;
        believeThreshold = _believeThreshold;
    }
    
    event SearchMoneyEvent(
        uint money    
    );
    
    event MoneyChange(
        bool isIncrease,
        uint amount,
        uint amount_after
    );
    
    event judegBelief(
        bool pass
    );
    
    event AddAccount(
        bool status,
        address companyAdd,
        string companyName,
        uint companyType, 
        uint money
    );


    
    function moneyIncrease(address _add, uint _money)public returns (bool ok) {
        if( add2company[_add].companyMoney == 0) return false;
        add2company[_add].companyMoney += _money;
        emit MoneyChange(true,_money, add2company[_add].companyMoney);
        return true;
    }

    function searchMoney(address _add) public returns (uint money){
        emit SearchMoneyEvent(add2company[_add].companyMoney);
        return add2company[_add].companyMoney;
    }
    function createBankAccount(string cname,address cadd,uint initMoney,uint ctype) public returns (bool ok) {
        if(add2company[cadd].companyMoney == 0){
                add2company[cadd] = Company({
                companyAdd:cadd,
                companyName:cname,
                companyType:ctype,
                companyMoney:initMoney
            });
            emit AddAccount(true,cadd, cname, ctype, initMoney);
            return true;
        }else{
            emit AddAccount(false,cadd, cname, ctype, initMoney);
            return false;
        }
    }
    function isValidAccount(address _add) public returns (bool ok){
        if(add2company[_add].companyMoney == 0){
            return false;
        }else{
            return true;
        }
    }
    function judgeIfBelieve (address cadd) public returns (bool ok){
        if(add2company[cadd].companyMoney > believeThreshold){
            emit judegBelief(true);
            return true;
        }else{
            emit judegBelief(false);
            return false;
        }
    }
    function moneyDecrease(address _add, uint _money)public returns (bool ok) {
        if( add2company[_add].companyMoney == 0 || add2company[_add].companyMoney <= _money) return false;
        add2company[_add].companyMoney -= _money;
        emit MoneyChange(false,_money, add2company[_add].companyMoney);
        return true;
    }

}