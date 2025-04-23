package com.antalyaotel.service;

import okhttp3.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

import java.util.*;

@Service
public class ChatService {

    @Value("${openai.api.key}")
    private String openaiApiKey;

    private static final Map<String, String> specialResponses = new HashMap<>();

    static {
        specialResponses.put("havuz", "Evet, otelimizde açık ve kapalı havuzlarımız bulunmaktadır.");
        specialResponses.put("kahvaltı", "Kahvaltımız sabah 07:30 ile 10:30 arasındadır.");
        specialResponses.put("spa", "Spa merkezimiz her gün 09:00 - 21:00 saatleri arasında hizmet vermektedir.");
        specialResponses.put("check-in", "Check-in işlemleri saat 14:00 itibariyle başlamaktadır.");
        specialResponses.put("check-out", "Check-out saati en geç 12:00'dir.");
        specialResponses.put("deniz manzaralı", "Deniz manzaralı odalarımız mevcuttur. Rezervasyon sırasında belirtmeniz yeterlidir.");
        specialResponses.put("wifi", "Otel genelinde ücretsiz Wi-Fi hizmetimiz mevcuttur.");
        specialResponses.put("otopark", "Misafirlerimiz için ücretsiz otopark hizmetimiz bulunmaktadır.");
        specialResponses.put("evcil hayvan", "Ne yazık ki evcil hayvan kabul edemiyoruz.");
        specialResponses.put("alkollü içecek", "Otelimizde alkollü ve alkolsüz içecek servisimiz vardır.");
        specialResponses.put("mini bar", "Tüm odalarımızda mini bar mevcuttur.");
        specialResponses.put("klima", "Tüm odalarımızda klima mevcuttur.");
        specialResponses.put("havalimanı servisi", "Havalimanı transfer hizmetimiz mevcuttur. Rezervasyon öncesinde bize bildirmeniz yeterlidir.");
    }

    public String getResponse(String message) {
        String lowerMessage = message.toLowerCase(Locale.ROOT);

        // Özel cevap kontrolü
        for (String keyword : specialResponses.keySet()) {
            if (lowerMessage.contains(keyword)) {
                return specialResponses.get(keyword);
            }
        }

        // GPT'yi geçici olarak pasifleştir
        return "Bu konuyla ilgili daha fazla bilgi almak için resepsiyonla iletişime geçebilirsiniz.";
    }

}
