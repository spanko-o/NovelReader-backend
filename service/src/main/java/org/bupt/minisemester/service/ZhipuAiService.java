package org.bupt.minisemester.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhipu.oapi.ClientV4;
import com.zhipu.oapi.Constants;
import com.zhipu.oapi.service.v4.model.ChatCompletionRequest;
import com.zhipu.oapi.service.v4.model.ChatMessage;
import com.zhipu.oapi.service.v4.model.ModelApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class ZhipuAiService {

    private final ClientV4 zhipuAiClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String BOT_NAME = "吐槽机器人";
    private static final String USER_NAME = "用户";
    private static final String USER_INFO = "用户是一名普通小说读者，喜欢看各种类型的小说。";
    private static final String BOT_INFO = "吐槽机器人是一个幽默风趣的虚拟角色，擅长对文本进行吐槽。它喜欢使用讽刺、调侃和幽默的语言风格。";

    @Autowired
    public ZhipuAiService(ClientV4 zhipuAiClient) {
        this.zhipuAiClient = zhipuAiClient;
    }

    public String generateRoast(String inputText) {

        List<ChatMessage> messages = new ArrayList<>();
        // 临时
        // 先向消息中加入角色信息描述
        messages.add(new ChatMessage("system", "角色设定：" + BOT_NAME + " 是一个幽默风趣的虚拟角色，擅长对文本进行吐槽。它喜欢使用讽刺、调侃和幽默的语言风格。"));
        messages.add(new ChatMessage("system", "用户设定：" + USER_NAME + " 是一名普通小说读者，喜欢看各种类型的小说。"));

        messages.add(new ChatMessage("user", inputText));

        String requestId = "roast-" + System.currentTimeMillis();

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model(Constants.ModelChatGLM4)
                //.setMeta(meta)
                .messages(messages)
                .requestId(requestId)
                .stream(Boolean.FALSE)
                .doSample(Boolean.TRUE)
                .maxTokens(150)
                .invokeMethod(Constants.invokeMethod)
                .build();

        ModelApiResponse response = zhipuAiClient.invokeModelApi(chatCompletionRequest);
        try{
            System.out.println(response.toString());
            return objectMapper.writeValueAsString(response.getData().getChoices().get(0).getMessage().getContent());
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }

    }
}
