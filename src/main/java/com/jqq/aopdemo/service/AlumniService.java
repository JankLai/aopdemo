package com.jqq.aopdemo.service;

import com.jqq.aopdemo.dao.AlumniDao;
import com.jqq.aopdemo.entity.Alumni;
import com.jqq.aopdemo.utils.GeneBirth;
import com.jqq.aopdemo.utils.GeneInfo;
import com.jqq.aopdemo.utils.GeneLoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AlumniService {

    @Autowired
    private AlumniDao alumniDao;

    public Integer initAlumni(){
        String WORDS="赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜\n" +
                "戚谢邹喻柏水窦章。云苏潘葛奚范彭郎。鲁韦昌马苗凤花方俞任袁柳酆鲍史唐。\n" +
                "费廉岑薛雷贺倪汤。滕殷罗毕郝邬安常。乐于时傅皮卞齐康伍余元卜顾孟平黄。\n" +
                "和穆萧尹姚邵湛汪。祁毛禹狄米贝明臧。计伏成戴谈宋茅庞熊纪舒屈项祝董梁。\n" +
                "杜阮蓝闵席季麻强。贾路娄危江童颜郭。梅盛林刁锺徐丘骆高夏蔡田樊胡凌霍。\n" +
                "虞万支柯昝管卢莫。经房裘缪干解应宗。丁宣贲邓郁单杭洪包诸左石崔吉钮龚。\n" +
                "程嵇邢滑裴陆荣翁。荀羊於惠甄麹家封。芮羿储靳汲邴糜松井段富巫乌焦巴弓。\n" +
                "牧隗山谷车侯宓蓬。全郗班仰秋仲伊宫。甯仇栾暴甘钭厉戎祖武符刘景詹束龙。\n" +
                "叶幸司韶郜黎蓟薄。印宿白怀蒲邰从鄂。索咸籍赖卓蔺屠蒙池乔阴鬱胥能苍双。\n" +
                "闻莘党翟谭贡劳逄。姬申扶堵冉宰郦雍。郤璩桑桂濮牛寿通边扈燕冀郏浦尚农。\n" +
                "温别庄晏柴瞿阎充。慕连茹习宦艾鱼容。向古易慎戈廖庾终暨居衡步都耿满弘。\n" +
                "匡国文寇广禄阙东。欧殳沃利蔚越夔隆。师巩厍聂晁勾敖融冷訾辛阚那简饶空。\n" +
                "曾毋沙乜养鞠须丰。巢关蒯相查后荆红。游竺权逯盖益桓公万俟司马上官欧阳。\n" +
                "夏侯诸葛闻人东方。赫连皇甫尉迟公羊。澹台公冶宗政濮阳淳于单于太叔申屠。\n" +
                "公孙仲孙轩辕令狐。锺离宇文长孙慕容。鲜于闾丘司徒司空亓官司寇仉督子车。\n" +
                "颛孙端木巫马公西漆雕乐正壤驷公良。拓跋夹谷宰父穀梁晋楚闫法汝鄢涂钦。\n" +
                "段干百里郭南门呼延归海羊舌微生。岳帅缑亢後有琴梁丘左丘东门西门。\n" +
                "商牟佘佴伯赏南宫墨哈谯笪年爱阳佟。";
        Random random = new Random();
        List<Alumni> alumniList=new ArrayList<>();

        for(int j=0;j<1000;j++) {
            Alumni alumni = new Alumni();
            StringBuffer sb = new StringBuffer();
            boolean flag;
            flag = false;
            char firstName;
            while (!flag) {
                firstName = WORDS.charAt(random.nextInt(WORDS.length()));
                if (firstName != '，' && firstName != '。' && firstName != '\n') {
                    sb.append(firstName);
                    flag = true;
                }
            }
            if (random.nextInt(2) == 1) {
                String str = "";
                byte[] b = new byte[2];
                int highPos = (176 + Math.abs(random.nextInt(39)));
                int lowPos = (161 + Math.abs(random.nextInt(93)));
                b[0] = (Integer.valueOf(highPos)).byteValue();
                b[1] = (Integer.valueOf(lowPos)).byteValue();
                try {
                    str = new String(b, "GBK");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    System.out.println("错误");
                }
                sb.append(str.charAt(0));

                highPos = (176 + Math.abs(random.nextInt(39)));
                lowPos = (161 + Math.abs(random.nextInt(93)));
                b[0] = (Integer.valueOf(highPos)).byteValue();
                b[1] = (Integer.valueOf(lowPos)).byteValue();
                try {
                    str = new String(b, "GBK");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    System.out.println("错误");
                }
                sb.append(str.charAt(0));

            } else {
                String str;
                str = "";
                int highPos = (176 + Math.abs(random.nextInt(39)));
                int lowPos = (161 + Math.abs(random.nextInt(93)));
                byte[] b = new byte[2];
                b[0] = (Integer.valueOf(highPos)).byteValue();
                b[1] = (Integer.valueOf(lowPos)).byteValue();
                try {
                    str = new String(b, "GBK");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    System.out.println("错误");
                }
                sb.append(str.charAt(0));
            }
            alumni.setName(sb.toString());


            //auto generate birthday
            //auto generate inSchoolDate
            //auto generate outSchoolDate
            GeneBirth geneBirth = new GeneBirth();
            String birthDay = geneBirth.randomDate("1978-1-1", "1997-12-31");
            String birthYear = birthDay.substring(0, 4);
            String inSchoolDate = String.valueOf(Integer.parseInt(birthYear) + 18);
            String outSchoolDate = String.valueOf(Integer.parseInt(birthYear) + 22);
            alumni.setBirthday(birthDay);
            alumni.setInSchoolDate(inSchoolDate);
            alumni.setOutSchoolDate(outSchoolDate);
            //auto generate workingCity
            GeneLoc lu = new GeneLoc();
            List<String> countryList = lu.getCountry();
            if (random.nextInt(40) == 2) {//国外工作
                String country = countryList.get(random.nextInt(countryList.size() - 1) + 1);
                List<String> randomProvinces = lu.getProvinces(country);
                if (randomProvinces.size() < 2) {
                    alumni.setWorkingCity(country);
                } else {
                    String province = randomProvinces.get(random.nextInt(randomProvinces.size() - 1) + 1);
                    List<String> randomCities = lu.getCities(country, province);
                    if (randomCities.size() < 2) {
                        alumni.setWorkingCity(country + province);
                    } else {
                        String city = randomCities.get(random.nextInt(randomCities.size() - 1) + 1);
                        alumni.setWorkingCity(country + province + city);
                    }

                }

            } else {                            //国内工作
                List<String> provinces = lu.getProvinces("中国");
                String province = provinces.get(random.nextInt(provinces.size() - 1) + 1);
                List<String> randomCities = lu.getCities("中国", province);
                String city = randomCities.get(random.nextInt(randomCities.size() - 1) + 1);
                alumni.setWorkingCity(province + city);
            }

            //auto generate workingUnit
            alumni.setWorkingUnit("XXX");
            //auto generate job
            alumni.setJob(GeneInfo.getJob(Integer.parseInt(inSchoolDate)));


            //auto generate sex
            //auto generate phone
            //auto generate email
            //auto generate weChat
            alumni.setPhoneNum(GeneInfo.getTel());
            alumni.setEmail(GeneInfo.getEmail(6, 10));
            alumni.setWechat(GeneInfo.getWeChat(6, 20));
            if (random.nextInt(2) == 1) {
                alumni.setSex("男");
                alumni.setName(GeneInfo.getChineseName(1));
            } else {
                alumni.setSex("女");
                alumni.setName(GeneInfo.getChineseName(0));
            }
            alumniList.add(alumni);
        }
        return alumniDao.initAlumni(alumniList);
    }
}
