package de.shifen.tokenizer.tokenizer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wltea.analyzer.IKSegmentation;
import org.wltea.analyzer.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ms404
 */
@RestController
public class TokenizerController {
    @RequestMapping("/api/v3/tokenize")
    public Map<String,Object> tokenizer(
           @RequestParam("text") String text
    ){
        List<String> lists= new ArrayList<>();
        IKSegmentation ikSeg = new IKSegmentation(new StringReader(text) , false);
        try {
            Lexeme l = null;
            while( (l = ikSeg.next()) != null){
                System.out.println(l);
                lists.add(l.getLexemeText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> result = new HashMap<>();
        result.put("status",200);
        result.put("data",lists);
        return result;
    }
}
