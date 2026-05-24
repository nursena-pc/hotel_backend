#  Otel Chatbot ve Rezervasyon Sistemi

Bu proje, yapay zeka destekli bir otel rezervasyon ve müşteri destek sistemidir. Spring Boot, Google Cloud API'leri, Dialogflow ve OpenAI GPT-3.5 teknolojileri ile entegre çalışacak şekilde geliştirilmiştir.

##  Özellikler

###  Genel Sistem Özellikleri
- Spring Boot tabanlı RESTful backend altyapısı
- JWT tabanlı kimlik doğrulama ve role-based yetkilendirme (admin/müşteri)
- Swagger / OpenAPI ile API dokümantasyonu
- Global exception handler, logging ve rate limiting
- CRUD tabanlı rezervasyon, kullanıcı ve takvim API'ları
- Spring Scheduler ile otomatik rezervasyon hatırlatma işlemleri
- SMTP üzerinden e-posta bildirimi gönderme
- MySQL veritabanı kullanımı
- Google Calendar API ile takvim entegrasyonu

###  Chatbot ve Sesli Asistan Özellikleri
- **Dialogflow** ile doğal dil işleme (NLU) altyapısı
- Kullanıcı niyetlerine göre özel yanıt üretme sistemi
- Google Cloud **Speech-to-Text** ile sesli girdiyi yazıya çevirme
- Google Cloud **Text-to-Speech** ile sistem yanıtlarını sesli hale getirme
- **OpenAI GPT-3.5 Turbo API** ile daha esnek ve akıllı yanıtlar üretme
- Özel soru-cevap sistemi (örneğin: rezervasyon durumu, kahvaltı saatleri, havuz kullanımı)

###  Test ve Geliştirme
- Ngrok ile lokal backend servislerinin dış dünyaya açılması
- Gerçek zamanlı chatbot testleri

---

##  Teknolojiler ve Araçlar

| Teknoloji | Açıklama |
|----------|----------|
| Spring Boot | Java tabanlı backend geliştirme |
| Dialogflow | NLP destekli chatbot |
| Google Cloud APIs | Speech-to-Text, Text-to-Speech, Calendar |
| OpenAI GPT-3.5 | Akıllı cevap üretme |
| MySQL | Veritabanı |
| JWT | Kimlik doğrulama |
| Swagger | API dokümantasyonu |
| Ngrok | Lokal sunucuyu public olarak yayınlama |
| SMTP | E-posta bildirim sistemi |
| Spring Scheduler | Otomatik job planlama |

---

##  Örnek Senaryolar

- “Yarın için çift kişilik oda var mı?” → Chatbot rezervasyon API’sine istek atar.
- “Kahvaltı saat kaçta başlıyor?” → Özel cevap sistemi üzerinden yanıt döner.
- “Şu an sesli olarak konuşuyorum, yer ayırtabilir miyim?” → Speech-to-Text + GPT + rezervasyon API entegrasyonu ile işlenir.

---

#  giriş ekranları
<img width="945" height="502" alt="image" src="https://github.com/user-attachments/assets/d06ec033-e67b-43b9-8a40-12aeaa9972be" />
<img width="943" height="501" alt="image" src="https://github.com/user-attachments/assets/4dc31310-c0d3-47ca-9d9b-2006c36e0d07" />

# kullanıcı giriş ekranı
<img width="945" height="502" alt="image" src="https://github.com/user-attachments/assets/2210625a-4f79-4742-b390-4fd60e6e1ccc" />

# admin giriş ekranı
<img width="945" height="502" alt="image" src="https://github.com/user-attachments/assets/7466ea6a-c643-4721-838e-8b5e78aa3ca0" />

# Rezervasyon Oluşturma ve Geçmiş Rezervasyonları Görüntüleme
<img width="867" height="460" alt="image" src="https://github.com/user-attachments/assets/e4a364b0-b558-42aa-87f2-5e6869cf2e0c" />
<img width="698" height="371" alt="image" src="https://github.com/user-attachments/assets/88740286-aca3-4034-96ba-1e36992da46f" />
<img width="945" height="181" alt="image" src="https://github.com/user-attachments/assets/c994875f-e9e1-48b9-912f-74e1c69aebfa" />
<img width="942" height="186" alt="image" src="https://github.com/user-attachments/assets/f7674a54-eb40-4ea4-a8c0-51e3b33f53e7" />
<img width="945" height="242" alt="image" src="https://github.com/user-attachments/assets/a0713ac1-8b38-4d07-8ad3-dc81e947a67b" />

# Örnek rezervasyon bilgilendirme e-mail ekranları
<img width="1136" height="534" alt="image" src="https://github.com/user-attachments/assets/516c8e36-380b-4c5f-90f4-472571996e25" />
<img width="1131" height="399" alt="image" src="https://github.com/user-attachments/assets/e89cf642-190a-49b9-a802-35bd1288e1f9" />
<img width="1128" height="332" alt="image" src="https://github.com/user-attachments/assets/0aff8a89-f72d-4fe1-a017-e4ec2539b455" />


# Yapay Zeka Robotu
<img width="938" height="498" alt="image" src="https://github.com/user-attachments/assets/50c4f16e-262a-4d01-ba2b-a875b8fb750c" />
<img width="346" height="614" alt="image" src="https://github.com/user-attachments/assets/7140cbc7-790a-40c7-8bb1-0285def9c012" />
<img width="346" height="614" alt="image" src="https://github.com/user-attachments/assets/ff1b7cbf-9468-4c2b-a81f-a79fe73ef4ee" />

# 	      Admin Paneli

---

# admin oda yönetim ekranı
<img width="951" height="505" alt="image" src="https://github.com/user-attachments/assets/0843c97a-5a5a-40c0-ad1c-4eb250031655" />
<img width="945" height="276" alt="image" src="https://github.com/user-attachments/assets/adf725d7-c23f-40b5-8c54-150b635b5a46" />
<img width="939" height="498" alt="image" src="https://github.com/user-attachments/assets/06736375-3bea-457a-94c1-2a70d8440a92" />




<img width="945" height="502" alt="image" src="https://github.com/user-attachments/assets/b5c827f7-6bb2-4d48-94e6-09669d1cc26f" />

<img width="939" height="498" alt="image" src="https://github.com/user-attachments/assets/046ab4e6-693c-4d58-9182-7a9a29a770c4" />
<img width="945" height="502" alt="image" src="https://github.com/user-attachments/assets/038c9384-acc3-41b0-af6a-842bb0b95883" />
<img width="945" height="502" alt="image" src="https://github.com/user-attachments/assets/fa149826-fa35-4d0d-91ed-0abc74ddc993" />


# Google Calendar API ile Takvim Entegrasyonu
<img width="875" height="441" alt="image" src="https://github.com/user-attachments/assets/8c80cb15-d941-4df4-852a-3841a6a0a8be" />




























