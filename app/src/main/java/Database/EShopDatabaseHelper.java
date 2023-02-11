package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ArrayAdapter;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import Session.Customer;
import Session.ProdConsuming;
import Session.Product;
import Session.ProductElement;
import Session.Rating;
import Session.Report;

public class EShopDatabaseHelper extends SQLiteOpenHelper {
    Context context;

    public EShopDatabaseHelper(Context context) {
        super(context, "Database", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"Customers\" (\"CustID\" INTEGER DEFAULT 0, \"CustName\" TEXT,\"Email\" TEXT UNIQUE,\"Password\" TEXT,\"Gender\" TEXT, \"Birthdate\" TEXT, \"job\" TEXT, PRIMARY KEY(\"CustID\" AUTOINCREMENT));");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"Orders\" ( \"OrdID\" INTEGER DEFAULT 0, \"OrdDate\" TEXT, \"CustID\" INTEGER, \"Address\" TEXT, PRIMARY KEY(\"OrdID\" AUTOINCREMENT));");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"Categories\" ( \"CatID\" INTEGER DEFAULT 0, \"CatName\" TEXT, PRIMARY KEY(\"CatID\" AUTOINCREMENT));");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"Products\" ( \"ProdID\" INTEGER DEFAULT 0, \"ProdName\" TEXT, \"Price\" NUMERIC, \"Quantity\" INTEGER, \"CatID\" INTEGER, \"Description\"TEXT, Image BLOB, PRIMARY KEY(\"ProdID\" AUTOINCREMENT));");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"OrderDetails\" ( \"OrdID\"INTEGER, \"ProdID\" INTEGER, \"Quantity\" INTEGER);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"Rate\" (OrdID INTEGER, Stars NUMERIC, Comment TEXT);");
        sqLiteDatabase.execSQL("INSERT INTO Categories(CatName) VALUES('Phones')");
        sqLiteDatabase.execSQL("INSERT INTO Categories(CatName) VALUES('Laptops')");
        sqLiteDatabase.execSQL("INSERT INTO Categories(CatName) VALUES('Headphones')");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (1,'Apple IPhone 11 With FaceTime',22799,10,1,'6.1-inch Liquid Retina IPS Touch Display\n" +
                "128 GB Internal Storage\n" +
                "12 + 12 MP Back Camera, 12 MP + TOF Front Camera\n" +
                "Hexa-core CPU (2x2.65 GHz + 4x1.8 GHz), 4 GB RAM\n" +
                "Li-Ion 3046 mAh Battery, Wireless Charging\n" +
                "Chipset: Apple A13 Bionic (7 nm+)\n" +
                "CPU: Hexa-core (2x2.65 GHz Lightning + 4x1.8 GHz Thunder)\n" +
                "GPU: Apple GPU (4-core graphics)\n" +
                "OS: iOS 13, Face ID',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (2,'Apple IPhone 13 Pro Max',46799,10,1,'6.7-inch Super Retina XDR OLED Display\n" +
                "256 GB Internal Storage, 6 GB RAM\n" +
                "12 + 12 + 12 + MP + 3D Lidar Scanner Back Camera, SL 3D + 12 MP Front Camera\n" +
                "Hexa-core CPU (2×3.22 GHz + 4xX.X GHz)\n" +
                "Li-Ion 4373 mAh Battery, Wireless Charging\n" +
                "OS: iOS 15, Face ID',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (3,'Infinix Smart 6',3510,10,1,'SIM: Dual SIM (Nano-SIM, dual stand-by)\n" +
                "Build:   Glass front, plastic back, plastic frame\n" +
                "Display\n" +
                "Type: IPS LCD, 500 nits (typ)\n" +
                "Size: 6.6 inches, 105.2 cm2\n" +
                "Resolution: 720 x 1600 pixels, 20:9 ratio (~266 ppi density)\n" +
                "Platform\n" +
                "OS: Android 11 (Go edition), XOS 7.6\n" +
                "Chipset: Unisoc SC9863A (28nm)\n" +
                "CPU: Octa-core (4x1.6 GHz Cortex-A55 & 4x1.2 GHz Cortex-A55)\n" +
                "GPU: IMG832\n" +
                "Memory\n" +
                "Card slot: microSDXC (dedicated slot)\n" +
                "Internal: 64GB 3GB RAM\n" +
                "eMMC 5.1\n" +
                "Main Camera\n" +
                "Dual:    13 MP, AF\n" +
                "0.8 MP, (depth)\n" +
                "Features: Dual-LED flash\n" +
                "Video: 1080p@30fps\n" +
                "Selfie Camera\n" +
                "Single: 8 MP\n" +
                "Features: LED flash\n" +
                "Video: 720p@30fps\n" +
                "Sound\n" +
                "Loudspeaker: Yes\n" +
                "3.5mm jack: Yes',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (4,'OPPO Reno6 5G',9999,10,1,'6.4 inches FHD+ AMOLED Punch-Hole Display\n" +
                "128GB ROM, 8GB RAM\n" +
                "64 + 8 + 2 MP Back Camera, 32 MP Front Camera\n" +
                "2x2.4 GHz Cortex-A78 & 6x2.0 GHz Cortex-A55\n" +
                "Li-Po 4300 mAh, non-removable\n" +
                "OS: Android 11, ColorOS 11.3\n" +
                "Chipset: MediaTek MT6877 Dimensity 900 5G (6 nm)\n" +
                "CPU: Octa-core (2x2.4 GHz Cortex-A78 & 6x2.0 GHz Cortex-A55)\n" +
                "GPU: Mali-G78 MC4',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (5,'OPPO Reno7 4G',10900,10,1,'Dimensions: 156.8 x 72.1 x 7.6 mm\n" +
                "Weight: 182 g\n" +
                "SIM: Dual SIM (Nano-SIM, dual stand-by)\n" +
                "Display\n" +
                "Type: AMOLED, 90Hz, 430 nits (typ), 600 nits (HBM), 750 nits (peak)\n" +
                "Size: 6.43 inches, 99.8 cm2 (~88.3% screen-to-body ratio)\n" +
                "Resolution: 1080 x 2400 pixels, 20:9 ratio (~409 ppi density)\n" +
                "Protection: Corning Gorilla Glass 5\n" +
                "Platform\n" +
                "OS: Android 11, ColorOS 12\n" +
                "Chipset: Snapdragon 680 4G \n" +
                "CPU: Octa-core (2x2.4 GHz Cortex-A78 & 6x2.0 GHz Cortex-A55)\n" +
                "GPU: Qualcomm SM7325\n" +
                "Memory\n" +
                "Card slot: No\n" +
                "Internal: 256GB 8GB RAM\n" +
                "Main Camera\n" +
                "Quad: 64 MP, f/1.7, 26mm (wide), 1/2.0, 0.7µm, PDAF\n" +
                "8 MP, f/2.2, 119˚ (ultrawide), 1/4.0, 1.12µm\n" +
                "2 MP, f/2.4, (macro)\n" +
                "Features: LED flash, HDR, panorama\n" +
                "Video: 4K@30fps, 1080p@30/60fps\n" +
                "Selfie Camera\n" +
                "Single: 32 MP, f/2.4, 26mm (wide), 1/2.8, 0.8µm\n" +
                "Features: Panorama\n" +
                "Video: 1080p@30fps\n" +
                "Sound\n" +
                "Loudspeaker: Yes\n" +
                "3.5mm jack: No',NULL);\n");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (6,'Samsung Galaxy A23',5399,10,1,'6.6 inches PLS LCD, 90Hz Display\n" +
                "128GB Storage, 4GB RAM\n" +
                "50 + 5 + 2 + 2 MP Back Camera, 8 MP Front Camera\n" +
                "Octa-core CPU: (2.4 GHz, 1.9 GHz)\n" +
                "Li-Po 5000 mAh, non-removable Battery, Fingerprint Sensor\n" +
                "OS:Android 12, One UI 4.1\n" +
                "Chipset: Snapdragon 680\n" +
                "CPU: Octa-core (2.4 GHz, 1.9 GHz)\n" +
                "Loudspeaker: Yes\n" +
                "3.5mm jack: Yes',NULL);\n");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (7,'Samsung Galaxy A72',11444,10,1,'SIM Card: Dual SIM (Nano-SIM, dual stand-by)\n" +
                "Screen: 6.7 inches 1080 x 2400 pixels\n" +
                "RAM: 8 GB\n" +
                "Internal memory: 128GB\n" +
                "Rear Camera: Quad: 64 MP + 12 MP + 5 MP + 5 MP\n" +
                "Selfie Camera: 32 MP\n" +
                "Color: Blue\n" +
                "Brand  : Samsung\n" +
                "Color  : Awesome Blue\n" +
                "Network 2G  : GSM 900 / 1800 / 1900\n" +
                "Network 3G  : HSDPA 900 / 1900 / 2100\n" +
                "4G Network     : LTE',NULL);\n");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (8,'XIAOMI Poco M4 Pro',8399,10,1,'Display Size In Inches: 6.43 inches\n" +
                "Processor CPU: Octa-core\n" +
                "Internal Memory Capacity In GB: 256 GB\n" +
                "Memory RAM In GB: 8 GB\n" +
                "Rear Camera: 64 MP + 8 MP + 2 MP\n" +
                "Front Camera: 16 MP \n" +
                "Battery: 5000 mAh\n" +
                "CPU Octa-core (2x2.05 GHz Cortex-A76 & 6x2.0 GHz Cortex-A55)\n" +
                "GPU Mali-G57 MC2',NULL);\n");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (9,'XIAOMI Redmi 10 2022',6500,10,1,'Display Size In Inches: 6.5 inches\n" +
                "Processor CPU: Octa-core\n" +
                "Internal Memory Capacity In GB: 128 GB\n" +
                "Memory RAM In GB: 6 GB\n" +
                "Rear Camera: 50 MP + 8 MP + 2 MP + 2 MP\n" +
                "Front Camera: 8 MP\n" +
                "Battery: 5000 mAh\n" +
                "Chipset ( MediaTek Helio G88 (12nm)\n" +
                "CPU ( Octa-core (2x2.0 GHz Cortex-A75 & 6x1.8 GHz Cortex-A55)\n" +
                "GPU (Mali-G52 MC2)\n" +
                "Memory card slot (microSDXC (uses shared SIM slot)\n" +
                "Internal ( 128GB 6GB RAM )\n" +
                "Main camera quad ( 50 MP, f/1.8, (wide), PDAF / 8 MP, f/2.2, 120˚ (ultrawide) / 2 MP, f/2.4, (macro) / 2 MP, f/2.4, (depth)\n" +
                "Features (LED flash, HDR, panorama) \n" +
                "Video ( 1080p@30fps )',NULL);\n");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (10,'XIAOMI Redmi 10C',5450,10,1,'DISPLAY: Size 6.71 inches, 106.5 cm2 (~82.0% screen-to-body ratio)\n" +
                "Chipset Qualcomm SM6225 Snapdragon 680 4G (6 nm)\n" +
                "MEMORY:Internal 128GB 4GB RAM\n" +
                "MAIN CAMERA ;Dual 50 MP, f/1.8, 26mm (wide), PDAF/2 MP, f/2.4, (depth)\n" +
                "Loudspeaker :Yes\n" +
                "3.5mm jack: Yes\n" +
                "Bluetooth: 5.0, A2DP, LE\n" +
                "BATTERY:Type Li-Po 5000 mAh, non-removable\n" +
                "Charging: Fast charging 18W\n" +
                "Chipset Qualcomm SM6225 Snapdragon 680 4G (6 nm)\n" +
                "CPU Octa-core (4x2.4 GHz Kryo 265 Gold & 4x1.9 GHz Kryo 265 Silver)\n" +
                "GPU Adreno 610',NULL);\n");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (11,'DELL 9PQPHQ3 - G15 5520',27999,10,2,'DELL 9PQPHQ3 - G15 5520 - Intel Core I7-12700H \n" +
                "- 16GB RAM - \n" +
                "512GB SSD - \n" +
                "15.6 FHD - \n" +
                "Ubuntu -\n" +
                "NVIDIA GeForce RTX 3050 4 GB\n" +
                "Inputs and Outputs\n" +
                "1SuperSpeed USB 3.2 Gen \n" +
                "1 SuperSpeed USB 3.2 Gen \n" +
                "1 Thunderbolt 4/USB Type-C port with DisplayPort with alt mode\n" +
                "1 SuperSpeed USB 3.2 Gen \n" +
                "1 HDMI 2.1\n" +
                "1 Power in\n" +
                "1 RJ45\n" +
                "1 Headphones/mic\n" +
                "Hardware\n" +
                "1 headset (headphone and microphone combo) port Stereo speakers with Realtek ALC3254, 2 W x 2.5 W Dual-array microphones\n" +
                "Wireless/Networking\n" +
                "Intel� Wi-Fi 6 AX201 (2x2) Wi-Fi\n" +
                "Bluetooth\n" +
                "Software\n" +
                "Power\n" +
                "Battery :  3-Cell Battery, 56WHr (Integra ted)\n" +
                "Body\n" +
                "Dimensions: ?26.9 x 357.3 x 272.11 mm\n" +
                "Weight: Starting at 2.5 kg \n" +
                "- Dark Grey',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (12,'DELL Alienware M15 R6 Gaming Laptop',52999,10,2,'DELL Alienware M15 R6 Gaming Laptop - Intel Core I7 - 16GB RAM - 512GB SSD - 6GB RTX3060 GPU - 15.6 Inch FHD Display - Windows 11\n" +
                "Dell Alienware M15 R6 Gaming Laptop\n" +
                "Intel Core™ i7 11800H\n" +
                "16GB RAM Memory\n" +
                "512GB SSD Storage\n" +
                "NVIDIA GeForce RTX 3060 6GB\n" +
                "Windows 11 - Dark Side of The moon\n" +
                "Keyboard English/Arabic',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (13,'DELL G15 5511 Gaming Laptop',32950,10,2,'DELL G15 5511 Gaming Laptop - Intel Core I7-11800H - 16GB DDR4 RAM - 512GB SSD - NVIDIA GeForce RTX 3060 6GB GDDR6 -15.6 Inch FHD 120Hz Display - Ubuntu - DARK SHADOW GREY\n" +
                "Processor: 11th Generation Intel® Core™ i7-11800H (24MB Cache, up to 4.6 GHz, 8 cores)\n" +
                "Graphics Card: NVIDIA® GeForce RTX™ 3060 6GB GDDR6\n" +
                "Memory: 16 GB DDR4, 3200 MHz, dual-channel\n" +
                "Hard Drive: 512GB M.2 PCIe NVMe Solid State Drive\n" +
                "Display: 15.6 inch FHD (1920 x 1080) 120Hz 250 nits WVA Anti- Glare LED Backlit Narrow Border Display\n" +
                "Operating System: Ubuntu',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (14,'DELL Laptop Inspiron G15 - 5511',29999,10,2,'DELL Laptop Inspiron G15 - 5511 - Intel Core I7-11800H - 16GB RAM -\n" +
                "512GB SSD - 15.6 Inch FHD - Nvidia RTX 3060 6G - Win10 Home - Grey\n" +
                "Wireless / Network:\n" +
                "Realtek RTL8111H PCI-e Gigabit ethernet controller\n" +
                "Intel Wi-Fi 6 AX201 (2x2) Wi-Fi + Bluetooth 5.1\n" +
                "Software:\n" +
                "Operating System: Windows 10 Home\n" +
                "Battery & Power:\n" +
                "3-Cell Battery, 56WHr (Integrated)\n" +
                "Adapter: 240 W\n" +
                "Dimensions & Weight:\n" +
                "Height: 23.3mm (0.91) - 2. Width: 357.2mm (14.06) - 3. Depth: 272.8mm (10.74) -\n" +
                "Starting Weight: 2.44 kg',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (15,'HP LAPTOP-HP-17',17849,10,2,'HP LAPTOP-HP-17 -dW3103NE 4D4J5EACi7-1165G7 - 8GB - 512GB SSD - GF MX450-2GB - 15.6\" HD 250 Nits - - Jet Black\n" +
                "Display\n" +
                "Screen Size: 15.6''''\n" +
                "Resolution: Full HD (1920 x 1080)\n" +
                "Graphics\n" +
                "Graphics Processor: NVIDIA® GeForce® MX450\n" +
                "Graphics Video RAM: 2 GB GDDR5 dedicated\n" +
                "Optical Drive\n" +
                "No\n" +
                "Inputs and Outputs\n" +
                "1 SuperSpeed USB Type-C® 5Gbps signaling rate\n" +
                "2 SuperSpeed USB Type-A 5Gbps signaling rate\n" +
                "1 HDMI 1.4b; 1 RJ-45; 1 AC smart pin\n" +
                "1 headphone/microphone combo\n" +
                "Wireless/Networking\n" +
                "Gigabit Ethernet\n" +
                "Wireless Realtek RTL8821CE-M 802.11a/b/g/n/ac (1x1)\n" +
                "Bluetooth® 4.2\n" +
                "Software\n" +
                "Operating System: Free DOS\n" +
                "Body\n" +
                "Dimensions (W x D x H): 35.85 x 24.2 x 1.99 cm\n" +
                "Weight: 1.75 kg',NULL);\n");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (16,'HP Pavilion X360 14-DY0143ne 2',17499,10,2,'HP Pavilion X360 14-DY0143ne 2-in-1 Laptop - Intel Core I5-1135G7 - 8GB RAM - 512GB SSD - 15.6-inch HD - Intel Iris X? Graphics - DOS - Silver - Arabic/English Keyboard\n" +
                "Inputs and Outputs:\n" +
                "1 SuperSpeed USB Type-C� 10Gbps signaling rate (USB Power Delivery, DisplayPort� 1.4, HP Sleep and Charge)\n" +
                "2 SuperSpeed USB Type-A 5Gbps signaling rate\n" +
                "1 HDMI 2.0\n" +
                "1 AC smart pin\n" +
                "1 headphone/microphone combo\n" +
                "Wireless/Networking:\n" +
                "Realtek Wi-Fi 6 (1x2) and Bluetooth� 5.2 combo (Supporting Gigabit data rate)\n" +
                "Software:\n" +
                "Operating System: FreeDOS\n" +
                "Power:\n" +
                "3-cell, 43 Wh Li-ion polymer\n" +
                "65 W Smart AC power adapter\n" +
                "Dimensions and Weight:\n" +
                "32.2 x 20.9 x 1.99 cm\n" +
                "1.52 kg',NULL);");


        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (17,'HP ProBook 450 G8 Laptop',20777,10,2,'HP ProBook 450 G8 Laptop - Intel Core I7-1165G7 - 8GB RAM - 512GB SSD - 15.6-inch FHD - Intel Iris X? Graphics - FreeDOS - Silver - English/Arabic Keyboard\n" +
                "Intel® Core™ i7-1165G7 CPU\n" +
                "8GB DDR4 RAM, 512GB SSD\n" +
                "15.6\" diagonal, FHD (1920 x 1080), IPS, narrow bezel, anti-glare, 250 nits, 45% NTSC\n" +
                "Integrated, Intel® Iris® X? Graphics\n" +
                "HP Long Life 3-cell, 45 Wh Li-ion Battery, OS: FreeDOS\n" +
                "SPECIFICATIONS\n" +
                "SKU: HP431CL17BPVDNAFAMZ\n" +
                "Model: 4K7J5EA\n" +
                "Size (L x W x H cm): 35.94 x 23.39 x 1.99 cm�\n" +
                "Color: Silver\n" +
                "Main Material: N/A',NULL);\n");

        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (18,'Lenovo IdeaPad 3-15IML05 Laptop',11000,10,2,'Lenovo IdeaPad 3-15IML05 Laptop - Intel Core I3 - 4GB RAM - 1TB HDD - 15.6-inch FHD - 2GB GPU - DOS - Abyss Blue\n" +
                "Optical Drive\n" +
                "None\n" +
                "Inputs and Outputs\n" +
                "2 x USB-A 3.1 Gen 1\n" +
                "1 x USB-A 2.0\n" +
                "1 x HDMI port\n" +
                "3.5 mm Combo Audio Jack (headphone and mic)\n" +
                "1 x SD Card Reader\n" +
                "Hardware\n" +
                "Camera: 0.3MP Fixed Focus camera with Dual Microphone\n" +
                "Audio: 2 x 1.5W speakers with Dolby Audio™\n" +
                "Wireless/Networking\n" +
                "Wi-Fi 802.11 b/g/n/ac 2x2\n" +
                "Bluetooth® 5.0\n" +
                "Software\n" +
                "Operating System: None\n" +
                "Power\n" +
                "Battery: 2 Cells\n" +
                "Power Adapter: 65W Round Tip Body Dimensions (W x D x H): 362.2 x 253.4 x 19.9 mm\n" +
                "Weight: Starting at 1.7 kg',NULL);");


        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (19,'Lenovo IdeaPad 5 Laptop',20849,10,2,'Lenovo IdeaPad 5 Laptop - 11th Intel Core I7-1165G7 - 16GB RAM - 512GB SSD - 2GB GPU - 15.6 Inch FHD IPS - Fingerprint Reader - Dos - Graphite Grey\n" +
                "Battery Integrated 57Wh\n" +
                "Power Adapter 65W Round Tip \n" +
                "Display 15.6\" FHD (1920x1080) IPS 300nits Anti-glare, 45% NTSC\n" +
                "Keyboard Backlit, Arabic \n" +
                "Case Color Graphite Grey\n" +
                "Surface Treatment Anodizing\n" +
                "Case Material Aluminium (Top), PC + ABS (Bottom)\n" +
                "Dimensions (WxDxH)  356.67 x 233.13 x 17.9-19.9 mm (14.04 x 9.18 x 0.7-0.78 inches)\n" +
                "Weight 1.66 kg (3.66 lbs)\n" +
                "Operating System None\n" +
                "Ethernet None\n" +
                "WLAN + Bluetooth 11ax, 2x2 + BT5.1',NULL);\n");

        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (20,'Lenovo V14 82C6006GED',6999,10,2,'Lenovo V14 82C6006GED – AMD 3020E - 4GB RAM - 1TB HDD – 14 Inch – DOS - Iron Grey\n" +
                "CPU :AMD 3020E, 4GB RAM, 1TB HDD\n" +
                "Graphics : AMD Radeon Graphics\n" +
                "14 Inch FHD (\u200E1280 x 800) Screen\n" +
                "Memory : 4GB Soldered DDR4-2666\n" +
                "Storage : 1TB HDD 5400rpm 2.5\"\n" +
                "Lenovo Laptop\n" +
                "Good Quality with a high end\n" +
                "Lenovo V14 82C6006GED - AMD A4 3020E, 4GB RAM, 1TB HDD, AMD Radeon Graphics, 14 Inch HD 220nits Anti-glare, Dos - Iron Grey\n" +
                "Easy to use\n" +
                "Personal Computer type\n" +
                "Package Dimensions    : \u200E49.4 x 31.4 x 7.4 cm; 2.24 Kilograms\n" +
                "Item Weight    : 2.24 KG',NULL);");

        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (21,'Beats Mrq82AeA Studio3',4763,10,3,'Height : 7.2 in./18.4 cm\n" +
                "Weight : 9.17 oz./260g\n" +
                "Other Features : Active Noise Cancelling, Foldable, With Remote and Mic, Inline Volume Control, Noise Isolation, Stereo Bluetooth, Wireless\n" +
                "Form Factor : Over ear\n" +
                "Connections : Bluetooth, Wireless\n" +
                "Bluetooth Compatibility : Bluetooth 4.0\n" +
                "Power Source : Battery\n" +
                "Batteries : Rechargeable lithium-ion\n" +
                "Bluetooth, Adaptive Noise Cancelling (ANC), ANC on/off function , On-board call and music controls, On-board volume control, Noise Isolation, Stereo Bluetooth, Wired playback via RemoteTalk cable, RemoteTalk cable with inline controls, LED Fuel Gauge, Charge via Micro-USB Cable',NULL);\n");

        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (22,'Beats Mwuh2Lla ',4688,10,3,'Product Features\n" +
                "Bluetooth Wireless\n" +
                "Pure Adaptive Noise Canceling\n" +
                "Built-In Mic for Taking Calls\n" +
                "Integrated Controls for Siri\n" +
                "On-Headphone Controls\n" +
                "Cushioned Earcups\n" +
                "Up to 22 Hours of Battery Life with ANC\n" +
                "Includes RemoteTalk Cable for Wired Use\n" +
                "Includes Case and Charging Cable',NULL);");

        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (23,'Beats Mxy82LlA Pro',4057,10,3,'Product Key Features\n" +
                "Color\n" +
                "Blue\n" +
                "Wireless Technology\n" +
                "Bluetooth\n" +
                "Connectivity\n" +
                "Bluetooth, Lightning\n" +
                "Form Factor\n" +
                "Ear-hook\n" +
                "Features\n" +
                "Call functions, Rechargeable Battery, Wireless Charging\n" +
                "Microphone Type\n" +
                "Built-In\n" +
                "Number of Earpieces\n" +
                "Double\n" +
                "Type\n" +
                "Canal Earbud (In Ear Canal)',NULL);");

        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (24,'Celebrat A23 Scalable',525,10,3,'Product details\n" +
                "Brand:Celebrat\n" +
                "Model Number:A23\n" +
                "Type:Over Ear\n" +
                "Compatible with:Multi\n" +
                "Connectivity:Wired/Wireless\n" +
                "Microphone Included:Yes\n" +
                "Noise Cancelling:Yes\n" +
                "Bluetooth Version:V5.0\n" +
                "Transmission Distance:10 M\n" +
                "Standby Time:About 80 Hours',NULL);");

        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (25,'Headphone_BingoZone',459,10,3,'Version of bluetooth: V5.0 + EDRTransmission Power: Class IIEffective distance: 10MBluetooth Sensitvity: - 108dBFrequency: 2.4 - 2.4835 GHZPlug type: 3.5mmBattery: 500mAhWorking hours: About 12 hoursCharging time: About 3-4 hours',NULL);\n");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (26,'JBL JR 310BT Wireless',1395,10,3,'Brand: JBL\n" +
                "Colour : Green\n" +
                "Easy to Use\n" +
                "Very comfy\n" +
                "Extremely compact\n" +
                "Make your headphones unique - with the JBL sticker set\n" +
                "Membrane size: 32 mm / 1.25\" loudspeaker driver for dynamic sound',NULL);\n");

        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (27,'JBL JR 3101 3 - Wireless',1395,10,3,'Brand: JBL\n" +
                "Colour : Red\n" +
                "Easy to Use\n" +
                "Very comfy\n" +
                "Extremely compact\n" +
                "Make your headphones unique - with the JBL sticker set\n" +
                "Membrane size: 32 mm / 1.25\" loudspeaker driver for dynamic sound',NULL);\n");

        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (28,'JBL T510 BT Wireless',1728,10,3,'Brand: JBL\n" +
                "Model Name: JBLT510BTBLKAM\n" +
                "Color: White\n" +
                "Form Factor: On Ear\n" +
                "Connectivity Technology: Bluetooth 5.0',NULL);");

        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (29,'JOYROOM JR-TL6',799,10,3,'Name: TWS Bilateral Earphones\n" +
                "Model: JR-TL6\n" +
                "Type: in ear\n" +
                "Bluetooth version: v5.0 Audio decoding protocol: SBC\n" +
                "Supporting protocols: HSP, HFP, A2DP, AVRCP\n" +
                "Frequency range: 50 Hz-10 kHz\n" +
                "Horn size: 0 10mm\n" +
                "Microphone sensitivity - 42 + 3dB\n" +
                "Battery capacity of earphone 40mAh Battery\n" +
                "capacity of charge case 300mAh\n" +
                "Music playing time: 3.5 hours\n" +
                "Call duration: 3 hours\n" +
                "Charging time of earphone: about 1.5 hours\n" +
                "Charging time of charge case: about 1.5 hours\n" +
                "Standby time: About 24h (connected)\n" +
                "Charging input: 5V-1A\n" +
                "Size of charging case: 61 50 * 29mm\n" +
                "Product material: ABS + silica gel\n" +
                "Net weight: 37.7g',NULL);");

        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (30,'SODO SD- 1007',377,10,3,'Wireless headphone\n" +
                "TF card and aux\n" +
                "Works wired and wireless\n" +
                "Bluetooth: 5.0\n" +
                "Battery: 3.7 250 MAh\n" +
                "Trigger size: 40mm\n" +
                "Charging time: 2 hours\n" +
                "Playback time: 6 ~ 8 hours',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO Customers(CustName,Email,Password, Gender, Birthdate, job) VALUES('George Samy'," +
                " 'george@gmail.com', '1111', 'Male', '11/12/2001', 'Software Engineer');");
        Bitmap[] bitmaps = new Bitmap[30];
        ByteArrayOutputStream[] byteArray = new ByteArrayOutputStream[bitmaps.length];
        for (int i = 0; i < bitmaps.length; i++) {
            String uri = "@drawable/p"+String.valueOf(i+1);
            bitmaps[i] = BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier(uri, null, context.getPackageName()));
            byteArray[i] = new ByteArrayOutputStream();
            bitmaps[i].compress(Bitmap.CompressFormat.JPEG, 100, byteArray[i]);
            addImage(i+1, byteArray[i].toByteArray(), sqLiteDatabase);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CUSTOMERS");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Orders");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Categories");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Products");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS OrderDetails");
    }

    public void addNewCustomer(String CustName, String Email, String Password, String Gender, String Birthdate, String job) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("INSERT INTO Customers(CustName,Email,Password, Gender, Birthdate, job)" +
                " VALUES ('" + CustName + "','" + Email + "','" + Password + "','" + Gender + "','" + Birthdate + "','" + job + "');");
    }

    public boolean isExist(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT Email FROM Customers Where Email = '" + email + "';", null);
        cursor.moveToFirst();
        boolean b = false;
        while (!cursor.isAfterLast()) {
            if (cursor.getString(0).equals(email)) {
                b = true;
            }
            cursor.moveToNext();
        }
        return b;
    }

    public ArrayAdapter<String> getProducts(String search) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1);
        Cursor cursor = getReadableDatabase().rawQuery("SELECT ProdName FROM Products WHERE ProdName  LIKE '%" + search + "%'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            adapter.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return adapter;
    }

    public boolean signInValidation(String em, String pass) {
        boolean check = false;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select Email,Password from Customers where Email ='" + em + "' and Password ='" + pass + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            check = true;
            cursor.moveToNext();
        }
        return check;
    }

    public Customer getCustomerInfo(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select CustID, CustName  from Customers where Email ='" + email + "'", null);
        cursor.moveToFirst();
        Customer customer = new Customer();
        while (!cursor.isAfterLast()) {
            customer.setId(cursor.getInt(0));
            customer.setUsername(cursor.getString(1));
            customer.setEmail(email);
            cursor.moveToNext();
        }
        return customer;
    }

    public void updatePassword(String newPassword, String email) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("update Customers set Password ='"+newPassword+"' where Email ='"+email+"'");
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("Password", newPassword);
//        sqLiteDatabase.update("Customers", contentValues, "Email like ?", new String[]{email});
    }

    public boolean changePassowordValidation(String em, String jo) {
        boolean check =false;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select Email,job from Customers where Email ='"+em+"' and job ='"+jo+"'",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            check=true;
            cursor.moveToNext();
        }
        return check;
    }
    public String[] getDeviceDetails(String name)
    {
        String[] Details = new String[5];
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor1=sqLiteDatabase.rawQuery("select Categories.CatName from Products,Categories where Products.CatID=Categories.CatID and Products.ProdName = '"+name+"'" ,null);
        Cursor cursor=sqLiteDatabase.rawQuery("select ProdName,Price,Description,ProdID from Products where ProdName  ='"+name+"' ",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            Details[0]=cursor.getString(0);
            Details[1]=cursor.getString(1);
            Details[2]=cursor.getString(2);
            Details[3]=cursor.getString(3);
            cursor.moveToNext();
        }
        cursor1.moveToFirst();
        while(!cursor1.isAfterLast()) {
            Details[4]=cursor1.getString(0);
            cursor1.moveToNext();
        }
        return Details;
    }
    public void addProductToCart(int productID,int quantity)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("INSERT INTO OrderDetails( ProdID, Quantity) Values('"+productID+"','"+quantity+"');");
    }
    public void addImage(int id, byte[] img, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Image", img);
        db.update("Products", contentValues, "ProdID = ?", new String[]{String.valueOf(id)});
    }
    public String[] getCategories() {
        ArrayList<String> strings = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT CatName FROM Categories", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            strings.add(cursor.getString(0));
            cursor.moveToNext();
        }
        String[] result = new String[strings.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = strings.get(i);
        }
        return result;
    }

    public Product[] getProductsInCategory(String CatName) {
        ArrayList<Product> strings = new ArrayList<Product>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Products.ProdName, Products.Price FROM Categories, Products WHERE Categories.CatID = Products.CatID and Categories.CatName = '"+CatName+"'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Product p = new Product();
            p.setName(cursor.getString(0));
            p.setPrice(cursor.getString(1));
            strings.add(p);
            cursor.moveToNext();
        }
        Product[] result = new Product[strings.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = new Product();
            result[i].setName(strings.get(i).getName());
            result[i].setPrice(strings.get(i).getPrice());
        }
        return result;
    }

    public Bitmap getImage(String ProdName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select Image from Products WHERE ProdName = '"+ProdName+"'", null);
        Bitmap bmp;
        c.moveToFirst();
        while (!c.isAfterLast()) {
            byte[] image = c.getBlob(0);
            if (image == null) {
                bmp = BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("@drawable/vector", null, context.getPackageName()));
            } else {
                bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            }
            c.moveToNext();
            return bmp;
        }
        return null;
    }

    public void addProductToCartInOrderDelails(int orderId,int productID,int quantity)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("INSERT INTO OrderDetails( OrdID,ProdID, Quantity) Values('"+orderId+"','"+productID+"','"+quantity+"');");
    }
    public void addOrderOfUser(int customertID)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("INSERT INTO Orders(CustID) Values('"+customertID+"');");

    }

    public String getOrderId(int customertID)
    {
        String id = null;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select max(OrdID) from Orders where CustID  ='"+customertID+"' ",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            id=cursor.getString(0);
            cursor.moveToNext();
        }
        return id;
    }
    public Product showCartDetails(int OrdID)
    {
        ArrayList<String> prodName = new ArrayList<String>();
        ArrayList<String> prodPrice = new ArrayList<String>();
        ArrayList<String> quantity = new ArrayList<String>();
        ArrayList<Bitmap> image = new ArrayList<Bitmap>();
        Product product;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select ProdName,Price,Image,OrderDetails.Quantity from Products,OrderDetails,Orders where Products.ProdID=OrderDetails.ProdID and Orders.OrdID=OrderDetails.OrdID and Orders.OrdID = '"+OrdID+"'"   ,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            prodName.add(cursor.getString(0));
            prodPrice.add(cursor.getString(1));
            quantity.add(cursor.getString(3));
            //TODO: Solve new products in cart error
//            byte[] i = cursor.getBlob(2);
//            image.add( BitmapFactory.decodeByteArray(i, 0 , i.length));
            image.add(getImage(cursor.getString(0)));
            cursor.moveToNext();
        }
        String[] prodName1 = new String[prodName.size()];
        String[] prodPrice1 = new String[prodPrice.size()];
        String[] Quantity1 = new String[quantity.size()];
        Bitmap[] image1=new Bitmap[image.size()];
        for (int i = 0; i < prodName1.length; i++) {
            prodName1[i] = prodName.get(i);
            prodPrice1[i] = prodPrice.get(i);
            Quantity1[i]=quantity.get(i);
            image1[i]=image.get(i);
        }
        product=new Product(prodName1,prodPrice1,image1,Quantity1);
        return product;
    }

    public void ordIncrementQuantity(String OrdID, String ProdName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE OrderDetails SET Quantity = Quantity+1 WHERE OrdID = '"+OrdID+"' AND ProdID = (SELECT ProdID FROM Products WHERE ProdName = '"+ProdName+"')");
    }

    public void ordDecrementQuantity(String OrdID, String ProdName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE OrderDetails SET Quantity = Quantity-1 WHERE OrdID = '"+OrdID+"' AND ProdID = (SELECT ProdID FROM Products WHERE ProdName = '"+ProdName+"')");
    }

    public void ordDeleteProduct(String OrdID, String ProdName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM OrderDetails WHERE OrdID = '"+OrdID+"' AND ProdID = (SELECT ProdID FROM Products WHERE ProdName = '"+ProdName+"')");
    }

    public void setDateAndAddressInOrders(String OrdID, String Date, String Address) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE Orders SET OrdDate = '"+Date+"', Address = '"+Address+"' WHERE OrdID = '"+OrdID+"'");
    }

    public void insertRate(String OrdID, String Stars, String Comments) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO Rate VALUES('"+OrdID+"', '"+Stars+"','"+Comments+"');");
    }

    public void insertNewOrder(String CustID) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO Orders(CustID) VALUES('"+CustID+"')");
    }

    public Report[] specificUserAndDateReport(String name, String date)
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ArrayList<Report> report = new ArrayList<Report>();
        int count=0;
        Cursor cursor=sqLiteDatabase.rawQuery("select Orders.OrdDate,Customers.CustName,Products.ProdName,OrderDetails.Quantity,Products.Price from Products,OrderDetails,Orders,Customers where Products.ProdID=OrderDetails.ProdID and Orders.OrdID=OrderDetails.OrdID and Customers.CustID=Orders.CustID and Customers.CustName='"+name+"' and Orders.OrdDate='"+date+"'  "  ,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Report r = new Report(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            report.add(r);
            cursor.moveToNext();
        }
        Report[] report1 = new Report[report.size()];
        for (int i = 0; i < report1.length; i++) {
            report1[i] = new Report();
            report1[i].setOrderDate(report.get(i).getOrderDate());
            report1[i].setCustomerName(report.get(i).getCustomerName());
            report1[i].setProductName(report.get(i).getProductName());
            report1[i].setProductPrice(report.get(i).getProductPrice());
            report1[i].setProductQuantity(report.get(i).getProductQuantity());
        }
        return report1;
    }
    public Report[] specificOrderDateReport(String date)
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ArrayList<Report> report = new ArrayList<Report>();
        Cursor cursor=sqLiteDatabase.rawQuery("select Orders.OrdDate,Customers.CustName,Products.ProdName,OrderDetails.Quantity,Products.Price from Products,OrderDetails,Orders,Customers where Products.ProdID=OrderDetails.ProdID and Orders.OrdID=OrderDetails.OrdID and Customers.CustID=Orders.CustID  and Orders.OrdDate='"+date+"'  "  ,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Report r = new Report(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            report.add(r);
            cursor.moveToNext();
        }
        Report[] report1 = new Report[report.size()];
        for (int i = 0; i < report1.length; i++) {
            report1[i] = new Report();
            report1[i].setOrderDate(report.get(i).getOrderDate());
            report1[i].setCustomerName(report.get(i).getCustomerName());
            report1[i].setProductName(report.get(i).getProductName());
            report1[i].setProductPrice(report.get(i).getProductPrice());
            report1[i].setProductQuantity(report.get(i).getProductQuantity());
        }
        return report1;
    }
    public Report[] specificUserReport(String name)
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ArrayList<Report> report = new ArrayList<Report>();
        Cursor cursor=sqLiteDatabase.rawQuery("select Orders.OrdDate,Customers.CustName,Products.ProdName,OrderDetails.Quantity,Products.Price from Products,OrderDetails,Orders,Customers where Products.ProdID=OrderDetails.ProdID and Orders.OrdID=OrderDetails.OrdID and Customers.CustID=Orders.CustID  and Customers.CustName='"+name+"' "  ,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Report r = new Report(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            report.add(r);
            cursor.moveToNext();
        }
        Report[] report1 = new Report[report.size()];
        for (int i = 0; i < report1.length; i++) {
            report1[i] = new Report();
            report1[i].setOrderDate(report.get(i).getOrderDate());
            report1[i].setCustomerName(report.get(i).getCustomerName());
            report1[i].setProductName(report.get(i).getProductName());
            report1[i].setProductPrice(report.get(i).getProductPrice());
            report1[i].setProductQuantity(report.get(i).getProductQuantity());
        }
        return report1;
    }

    public Report[] showAllDataInReport()
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ArrayList<Report> report = new ArrayList<Report>();
        Cursor cursor=sqLiteDatabase.rawQuery("select Orders.OrdDate,Customers.CustName,Products.ProdName,OrderDetails.Quantity,Products.Price from Products,OrderDetails,Orders,Customers where Products.ProdID=OrderDetails.ProdID and Orders.OrdID=OrderDetails.OrdID and Customers.CustID=Orders.CustID " ,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Report r = new Report(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            report.add(r);
            cursor.moveToNext();
        }
        Report[] report1 = new Report[report.size()];
        for (int i = 0; i < report1.length; i++) {
            report1[i] = new Report();
            report1[i].setOrderDate(report.get(i).getOrderDate());
            report1[i].setCustomerName(report.get(i).getCustomerName());
            report1[i].setProductName(report.get(i).getProductName());
            report1[i].setProductPrice(report.get(i).getProductPrice());
            report1[i].setProductQuantity(report.get(i).getProductQuantity());
        }
        return report1;
    }

    public String[] showCategories() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT CatName FROM Categories", null);
        cursor.moveToFirst();
        ArrayList<String> res = new ArrayList<String>();
        while (!cursor.isAfterLast()) {
            res.add(cursor.getString(0));
            cursor.moveToNext();
        }
        return res.toArray(new String[res.size()]);
    }

    public void updateProduct(String productName,String quantity,String price,String description, String oldProdName)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("update Products set ProdName ='"+productName+"' , Quantity='"+quantity+"' , Price='"+price+"' , Description='"+description+"' where ProdName='"+oldProdName+"' ");
    }

    public void updateCategory(String oldName, String newName)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("update Categories set CatName ='"+newName+"' where CatName='"+oldName+"'");
    }

    public void deleteCategory(String categoryName)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM Categories WHERE CatName='"+categoryName+"'");
    }

    public void deleteProduct(String productName)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM Products WHERE ProdName='"+productName+"'");
    }

    public void addProduct(String productName,String quantity,String price,String description, String CatID)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("insert into Products(ProdName,Price,Quantity,Description,CatID) values ('"+productName+"','"+price+"','"+quantity+"','"+description+"','"+CatID+"');");
    }

    public void addCategory(String categoryName)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("insert into Categories (CatName)values('"+categoryName+"')");
    }

    public String[] showProductsByCatName(String CatName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Products.ProdName FROM Products, Categories WHERE Products.CatID = Categories.CatID AND Categories.CatName = '"+CatName+"'",null);
        cursor.moveToFirst();
        ArrayList<String> res = new ArrayList<String>();
        while (!cursor.isAfterLast()) {
            res.add(cursor.getString(0));
            cursor.moveToNext();
        }
        return res.toArray(new String[res.size()]);
    }

    public ProductElement getProductInfo(String ProdName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT ProdName, Price, Quantity, Description FROM Products WHERE ProdName = '"+ProdName+"'",null);
        cursor.moveToFirst();
        ProductElement p = new ProductElement();
        while (!cursor.isAfterLast()) {
            p.setProdName(cursor.getString(0));
            p.setProdPrice(cursor.getString(1));
            p.setProdQuantity(cursor.getString(2));
            p.setProdDescription(cursor.getString(3));
            cursor.moveToNext();
        }
        return p;
    }

    public String getCatIDViaCatName(String CatName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT CatID From Categories WHERE CatName = '"+CatName+"'", null);
        cursor.moveToFirst();
        String res = "";
        while (!cursor.isAfterLast()) {
            res = cursor.getString(0);
            cursor.moveToNext();
        }
        return res;
    }

    public ProdConsuming[] getQuantityFromOrderDetails(int OrdID)
    {
        ArrayList<ProdConsuming> res = new ArrayList<ProdConsuming>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT ProdID, Quantity FROM OrderDetails Where OrdID = '" + OrdID + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            res.add(new ProdConsuming(cursor.getString(0), cursor.getString(1)));
            cursor.moveToNext();
        }
        return res.toArray(new ProdConsuming[res.size()]);
    }
    public void updateQuantity(int OrdID){
        ProdConsuming[] consumings = getQuantityFromOrderDetails(OrdID);
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < consumings.length; i++) {
            db.execSQL("UPDATE Products SET Quantity = Quantity - "+consumings[i].getQuantity()+" WHERE ProdID = "+consumings[i].getProdID()+"");
        }
    }
    public Product prcentageofsell(){
        Product product;
        ArrayList<String> prodName = new ArrayList<String>();
        ArrayList<String> sumqunitity = new ArrayList<String>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT Products.ProdName, sum(OrderDetails.Quantity) FROM Products, OrderDetails WHERE Products.ProdID = OrderDetails.ProdID GROUP BY Products.ProdName;",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            prodName.add(cursor.getString(0));
            sumqunitity.add(cursor.getString(1));
            cursor.moveToNext();
        }
        String[] prodname = new String[prodName.size()];
        String[] sumqunit = new String[sumqunitity.size()];
        for (int i = 0; i < prodname.length; i++) {
            prodname[i] = prodName.get(i);
            sumqunit[i] = sumqunitity.get(i);
        }
        product=new Product(prodname,sumqunit);
        return product;
    }

    public Rating[] getFeedbacks() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Customers.CustName, Rate.Stars, Rate.Comment FROM Customers, Rate, Orders WHERE Orders.OrdID = Rate.OrdID AND Orders.CustID = Customers.CustID;",null);
        ArrayList<Rating> res = new ArrayList<Rating>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            res.add(new Rating(cursor.getString(0), Float.parseFloat(cursor.getString(1)), cursor.getString(2)));
            cursor.moveToNext();
        }
        return res.toArray(new Rating[res.size()]);
    }
}
