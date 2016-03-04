##Game Rule
- 需要設計一個Slot Game，本系統已提供遊戲所需要的config
- 已提供用戶自定輸入的function . 方便你測試
- 你需要提供一個spin function . 內容是每次轉動5個軸 轉動到5個相同的 symbol 便賠相應賠率
- output 的format 需要是json.(格式如下

##相關資料:
- playedSpin 相等於5個軸的結果(需要和config array position 一致

##提供應用Function :

Rng.rng(min, max) 產生隨機數字

## Example
```
"[{
 ""context"": {},
 ""event"": ""gameStart""
}, {
 ""context"": [63, 51, 2, 15, 8],
 ""event"": ""playedSpin""
}, {
 ""context"": [{
    ""symbol"": ""CUP"",
    ""num_of_sys"":5,
    ""context"": {
     ""Payline"": [1, 1, 1, 1, 1]
    },
    ""pay"": 900
   }
 ""event"": ""spinWin""
}, {
 ""context"": {},
 ""event"": ""gameEnd""
}]"
```

##中獎例子

| | | | |
| CUP | CUP | CUP | CUP | CUP |
| | | | |

- 賠300