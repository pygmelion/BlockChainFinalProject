pragma solidity ^0.4.21;

contract BankInterface{
    function createBankAccount(string cname,address cadd,uint initMoney,uint ctype) public returns (bool ok);
    function judgeIfBelieve (address cadd) public returns (bool ok);
    function moneyIncrease(address _add, uint _money)public returns (bool ok);
    function moneyDecrease(address _add, uint _money)public returns (bool ok);
    function isValidAccount(address _add) public returns (bool ok);
    function searchMoney(address _add) public returns (uint money);
}

contract Receipy3{
    struct Receipy{
        bool ifBelief;
        address fromAdd;
        address toAdd;
        uint amount;
    }

    address public BankAdd;
    address public BankUserAdd;
    BankInterface binterface;
    uint public number;
    mapping(address => Receipy[]) add2receipier;
    mapping(address => Receipy[]) add2receipiee;

    event refreshReceipyEvent(
        bool ifBelief,
        address fromAdd,
        address toAdd,
        uint amount
    );
    
    event createReceipyEvent(
        bool ifPass
    );
    event deleteReceipyEvent(
        bool ifPass
    );
    event logReceipyEvent(
        bool ifBelief,
        address fromAdd,
        address toAdd,
        uint amount
    );
    event transferReceipyFailedEvent(
        string _log
    );
    event transferReceipyEvent(
        bool ifBelief,
        address oriFromAdd,
        address oriToAdd,
        address toAdd,
        uint amount,
        bool isOriDeleted
    );
    event payForReceipyErrorEvent(
        string _log
    );
    event payForReceipyEvent(
        bool isPayAll,
        address fromAdd,
        address toAdd,
        uint amount
    );
    event bankHelpErrorEvent(
        string _log
    );
    event bankHelpEvent(
        address fromAdd,
        address toAdd,
        uint amount
    );
    
    constructor(){
        BankUserAdd = 0xbf75837302328e53e3f4eac8334d1688f1c33a5b;
        BankAdd = 0x364c33ff2c488e520d9f6384f76135ecb06aab35;
        binterface = BankInterface(BankAdd);
        binterface.createBankAccount("Bank", BankUserAdd, 1000000, 0);
    }
    
    


   
    
    function logAllReceipy(address _add) public {
        
        for(uint i = 0; i < add2receipiee[_add].length; i++){
            Receipy storage temp = add2receipiee[_add][i];
            if(temp.amount == 0) continue;
            emit logReceipyEvent(
                temp.ifBelief,
                temp.fromAdd,
                temp.toAdd,
                temp.amount
            );
        }
        for(i = 0; i < add2receipier[_add].length; i++){
            temp = add2receipier[_add][i];
            if(temp.amount == 0) continue;

            emit logReceipyEvent(
                temp.ifBelief,
                temp.fromAdd,
                temp.toAdd,
                temp.amount
            );
        }
    }
     function receipy2Money(address _from, address _to, uint _amount) public returns(bool ok){
        bool haveExistedReceipy_to = false;
        for(uint i = 0; i < add2receipier[_to].length; i++){
            Receipy storage temp = add2receipier[_to][i];
            uint orilength = add2receipier[_to].length;
            if(temp.toAdd == _to && temp.fromAdd == _from && temp.amount != 0) {
                haveExistedReceipy_to = true;
                if (temp.amount < _amount){
                    emit payForReceipyErrorEvent("Amount more than ori amount");
                    return false;
                }
                uint temp_money = binterface.searchMoney(_from);
                if(temp_money < _amount){
                    emit payForReceipyErrorEvent("Amount more than from's money");
                    return false;
                }
                if(add2receipier[_to][i].amount == _amount){
                    if(!deleteReceipy(_from, _to)){
                        emit payForReceipyErrorEvent("delete ori receipy failed");
                        return false;
                    }
                    binterface.moneyIncrease(_to, _amount);
                    binterface.moneyDecrease(_from, _amount);
                    emit payForReceipyEvent(true, _from, _to, _amount);
                    return true;
                }else{
                    add2receipier[_to][i].amount -= _amount;
                    for(uint j = 0; j < add2receipiee[_from].length; j++){
                        temp = add2receipiee[_from][j];
                        if(temp.fromAdd == _from && temp.toAdd == _to && temp.amount != 0){
                            add2receipiee[_from][j].amount -= _amount;
                            break;
                        }
                    }
                    binterface.moneyIncrease(_to, _amount);
                    binterface.moneyDecrease(_from, _amount);
                    emit payForReceipyEvent(false,_from, _to, _amount);
                    return true;
                }
                break;
            }
        }
        if(!haveExistedReceipy_to){
            emit payForReceipyErrorEvent("can't faid matching receipy");
            return false;
        }
    }
    function CreateReceipy(address _from, address _to, uint _amount) public returns (bool ok){
        if(binterface.isValidAccount(_from) && binterface.isValidAccount(_to)){
            bool ifFromBelief = binterface.judgeIfBelieve(_from);
            bool sameReceipyExist = false;
            
            for(uint i = 0; i < add2receipiee[_from].length; i++){
                Receipy storage temp = add2receipiee[_from][i];
                if(temp.amount != 0 && temp.fromAdd == _from && temp.toAdd == _to){
                    sameReceipyExist = true;
                    add2receipiee[_from][i].amount += _amount;
                } 
            }
            
            if(sameReceipyExist){
                 for( i = 0; i < add2receipier[_to].length; i++){
                    temp = add2receipier[_to][i];
                    if(temp.amount != 0 && temp.fromAdd == _from && temp.toAdd == _to){
                        add2receipier[_to][i].amount += _amount;
                    } 
                }
                emit refreshReceipyEvent( 
                ifFromBelief,
                _from,
                _to,
                _amount);
                return true;
            }
            
            add2receipiee[_from].push(Receipy({
                ifBelief:ifFromBelief,
                fromAdd:_from,
                toAdd:_to,
                amount:_amount
            }));
            add2receipier[_to].push(Receipy({
                ifBelief:ifFromBelief,
                fromAdd:_from,
                toAdd:_to,
                amount:_amount
            }));
            emit createReceipyEvent(true);
            emit refreshReceipyEvent( 
                ifFromBelief,
                _from,
                _to,
                _amount);
            return true;
        }else{
            emit createReceipyEvent(false);
            return false;
        }
    }

    function receipy2BankMoney(address _from, address _to , uint _amount)public returns (bool ok){
        if (! transferReceipy( _from,  _to, BankUserAdd,  _amount)){
            emit bankHelpErrorEvent("transfer Receipy failed");
            return false;
        }else{
            binterface.moneyIncrease(_to, _amount);
            binterface.moneyDecrease(BankUserAdd, _amount);
            emit bankHelpEvent(_from, _to, _amount);
            return true;
        }
    }
    function transferReceipy(address _from, address _to, address _newTo, uint _amount) public returns (bool ok){
        if(!(binterface.isValidAccount(_from) && binterface.isValidAccount(_to) && binterface.isValidAccount(_newTo))){
            emit transferReceipyFailedEvent("invalid _from/_to address");
            return false;
        }

        bool ifFromBelief = binterface.judgeIfBelieve(_from);
        
        if( !ifFromBelief ){
            emit transferReceipyFailedEvent("not believed error");
            return false;
        }
        bool haveExistedReceipy_to = false;
        for(uint i = 0; i < add2receipier[_to].length; i++){
            Receipy storage temp = add2receipier[_to][i];
            uint orilength = add2receipier[_to].length;
            if(temp.toAdd == _to && temp.fromAdd == _from && temp.amount != 0) {
                haveExistedReceipy_to = true;
                if (temp.amount < _amount){
                    emit transferReceipyFailedEvent("Amount more than ori amount");
                    return false;
                }
                if(add2receipier[_to][i].amount == _amount){
                    if(!deleteReceipy(_from, _to)){
                        emit transferReceipyFailedEvent("delete ori receipy failed");
                        return false;
                    }
                    emit deleteReceipyEvent(true);
                    emit transferReceipyEvent(ifFromBelief,_from, _to, _newTo, _amount, true);
                    CreateReceipy(_from, _newTo, _amount);
                    return true;
                }else{
                    add2receipier[_to][i].amount -= _amount;
                    for(uint j = 0; j < add2receipiee[_from].length; j++){
                        temp = add2receipiee[_from][j];
                        if(temp.fromAdd == _from && temp.toAdd == _to && temp.amount != 0){
                            add2receipiee[_from][j].amount -= _amount;
                            break;
                        }
                    }
                    emit transferReceipyEvent(ifFromBelief,_from, _to, _newTo, _amount, false);
                    CreateReceipy(_from, _newTo, _amount);
                    return true;
                }
                break;
            }
        }
        if(!haveExistedReceipy_to){
            emit transferReceipyFailedEvent("can't faid matching receipy");
            return false;
        }
    }
    function deleteReceipy(address _from, address _to)public returns(bool ok){
        for(uint i = 0; i < add2receipier[_to].length; i++){
            Receipy storage temp = add2receipier[_to][i];
            if(temp.fromAdd == _from && temp.toAdd == _to && temp.amount != 0){
                delete add2receipier[_to][i];
            }
        }
        for(uint j = 0; j < add2receipiee[_from].length; j++){
            temp = add2receipiee[_from][j];
            if(temp.fromAdd == _from && temp.toAdd == _to && temp.amount != 0){
                delete add2receipiee[_from][j];
                return true;
            }
        }
        return false;
    }

}