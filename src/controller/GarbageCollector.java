package controller;

import model.adt.MyDictionary;
import model.adt.MyHeap;
import model.state.ProgramState;
import model.type.RefType;
import model.value.RefValue;
import model.value.Value;

import java.util.HashSet;
import java.util.Set;

public class GarbageCollector {
    private final Set<Integer> addresses =  new HashSet<>();

    public void collect(ProgramState state) {
        var symTable =  state.symTable();
        var heapTable =  state.heapTable();

        MyHeap<Integer, Value> heap = heapTable.getHeap();
        MyDictionary<String, Value> symbolT = symTable.getDict();

        for (var variable : symbolT.entrySet()) {
            var varValue = variable.getValue();
            if (varValue instanceof RefValue) {
                RefValue  refValue = (RefValue) varValue;
                int adr = refValue.address();
                if (adr != 0) {
                    addresses.add(adr);
                    var locType = refValue.getLocationType();
                    while (locType instanceof RefType) {
                        RefValue nextVal = (RefValue) heap.get(adr);
                        int nextAdr = nextVal.address();
                        addresses.add(nextAdr);
                        locType = nextVal.getLocationType();
                    }
                }
            }
        }
        heapTable.garbage(addresses);
    }
}


