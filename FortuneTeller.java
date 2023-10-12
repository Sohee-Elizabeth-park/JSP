package com.example.servlet04;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
//import java.util.Random;

@WebServlet("/FortuneTeller")
public class FortuneTeller extends HttpServlet {
    //id, age, sex 값 전달 받은 후 조합, 고유 숫자 생성
    //받은 아이디, 나이, 성별 조합 하나라도 다르면 다른 숫자
    private static int getUniqueNum(String id, String age, String sex) {
        String combinedValue = id + age + sex;
        // Use a simple hash code to generate a unique number
        int uniqueNum = combinedValue.hashCode();

        return uniqueNum;
    }

    //고유한 숫자 -> 1) 운세 데이터 랜덤 보여주기
    //오늘의 운세 데이터 배열
    private String[] fortuneList = {
            "오늘은 당신의 미소가 주변을 환하게 만들 것입니다. 어떤 어려움도 당신의 긍정적인 마음으로 극복할 수 있을 것입니다.",
            "오늘은 행운의 날! 뜻하지 않은 좋은 소식이 찾아올 것입니다. 기대를 갖고 새로운 가능성을 찾아보세요.",
            "오늘은 당신의 창조적인 능력이 빛을 발할 날입니다. 새로운 아이디어가 떠오를 것이고, 그것이 큰 성과로 이어질 것입니다.",
            "오늘은 주변 사람들과의 소통이 원만하게 이루어질 날입니다. 이야기를 나누며 새로운 인연을 만나게 될 수도 있습니다.",
            "오늘은 당신의 노력이 인정받을 때입니다. 어떤 일이든 최선을 다하면 성과가 있을 것이니 포기하지 마세요.",
            "오늘은 휴식을 취하고 자신을 돌보는 것이 중요한 날입니다. 마음과 몸의 피로를 풀고 새로운 에너지를 얻을 수 있을 것입니다.",
            "오늘은 당신의 자신감이 높아질 때입니다. 자신의 능력을 믿고 도전하는 것에 망설이지 마세요.",
            "오늘은 주변 환경이 조금 복잡할 수 있습니다. 하지만 당신의 평온한 태도로 상황을 잘 헤쳐나갈 수 있을 것입니다.",
            "오늘은 당신의 미래에 대한 계획을 세우기 좋은 날입니다. 꿈과 목표를 다짐하고 그에 맞는 행동을 시작해보세요.",
            "오늘은 당신의 따뜻한 마음이 주변을 감동시킬 것입니다. 작은 친절이 큰 기쁨으로 돌아올 것입니다.",
            "오늘은 긍정적인 에너지가 가득할 것이며, 새로운 기회를 발견할 수 있을 것입니다.",
            "오늘은 조용한 하루를 보낼 것으로 예상되며, 고요함을 즐기며 내면의 평화를 찾을 수 있을 것입니다.",
            "금일은 긍정적인 사회적 상호작용이 있을 것이며, 새로운 인연을 만나게 될 것입니다.",
            "오늘은 건강에 유의해야 할 날로, 식사와 운동을 소홀히 하지 않는 것이 중요할 것입니다.",
            "금일은 재물 운이 빛날 것으로 예상되며, 경제적으로 긍정적인 결과를 얻을 수 있을 것입니다.",
            "오늘은 창의적인 아이디어가 넘쳐흐를 것이며, 문제 해결에 도움이 될 것입니다.",
            "오늘은 감정적으로 안정을 찾을 수 있을 것으로 보이며, 가족과의 시간을 중요히 여겨보세요.",
            "금일은 여행이나 모험을 계획하는 것이 좋을 것으로 예상되며, 새로운 경험을 즐길 수 있을 것입니다.",
            "오늘은 목표를 달성하기 위해 집중력을 발휘할 수 있는 날로 예상됩니다.",
            "금일은 예상치 못한 변화가 있을 수 있으니, 변화에 대비하는 마음가짐을 가질 필요가 있을 것입니다.",
            "오늘은 어둠의 기운이 느껴집니다. 조심스럽게 행동하고 주변의 의심스러운 상황을 피하세요.",
            "오늘은 불길한 예감이 떠오릅니다. 주위를 면밀히 살펴보고, 소중한 결정을 내리기에는 좋지 않은 날입니다.",
            "오늘은 무서운 일들이 벌어질 수 있는 날입니다. 대비책을 세워두고 안전을 최우선으로 생각하세요.",
            "오늘은 가려진 위협이 숨어있을 수 있습니다. 주변 환경에 민감하게 반응하고, 긴장을 늦추지 마세요.",
            "오늘은 알 수 없는 곳에서 무서운 느낌이 들 수 있습니다. 주변의 이상한 현상에 경계를 기르며 지나가세요.",
            "오늘은 무서운 꿈이나 예감이 당신을 괴롭힐 수 있습니다. 이런 감정을 무시하지 말고 주의 깊게 살피세요.",
            "오늘은 기운이 어둡고 무섭게 느껴집니다. 외부의 영향에서 떨어지지 않도록 주의하세요.",
            "오늘은 무서운 사건이 예기치 않게 찾아올 수 있습니다. 주변을 꼼꼼히 주시하고 대비책을 갖추세요.",
            "오늘은 무서운 곳에서의 방문이나 통화가 예상됩니다. 의문의 상황에서는 신중한 선택이 필요합니다.",
            "오늘은 무서운 미래의 그림자가 당신을 따라다닐 것입니다. 신중한 선택과 대비를 갖추어 미래에 대비하세요.",
            "오늘은 당신이 미소 짓게 될 특별한 날! 어떤 상황에서도 '웃음 속에 행운이 있다'는 것을 기억하며 즐겁게 보내세요!"
    };

    //오늘의 운세 데이터 배열 index에 랜덤값 부여 메서드 생성
    public String getRandomFortune() {
        int randomIndex = (int)(Math.random()*fortuneList.length);
        return fortuneList[randomIndex];
    }

    //고유한 숫자 -> 2) 하루 동안만 같은 운세 데이터 보여주기 (날짜 메서드)




public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //사용자가 입력한 id, age, sex 값 전달받기
        String id = request.getParameter("id");
        String age = request.getParameter("age");
        String sex = request.getParameter("sex");

        //사용자가 입력한 값들을 조합하여 하나의 고유 숫자 만드는 메서드 불러와서 변수에 저장
        int uniqueNum = getUniqueNum(id, age, sex);

        //운세 데이터 배열 인덱스에 랜덤값 부여해서 랜덤 운세 데이터 보여주는 메서드 불러와서 변수에 저장
        //String randomFortune = getRandomFortune();

        Calendar calendar = new GregorianCalendar();
         int currentDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);

         // Get or generate a new random fortune for the current day
        String randomFortune = getFortuneForCurrentDay(uniqueNum, currentDayOfYear);



    PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>uniqueNum : "+uniqueNum+".</h1>");
        out.println("<h1>Today's Fortune: " + randomFortune + "</h1>");
        out.println("</body>");
        out.println("</html>");
    }

    private String getFortuneForCurrentDay(int uniqueNum, int currentDayOfYear) {
        String storedKey = uniqueNum + "-" + currentDayOfYear;

        // Check if a fortune has already been generated for this uniqueNum today
        if (getServletContext().getAttribute(storedKey) != null) {
            return (String) getServletContext().getAttribute(storedKey);
        }

        // If it's a new day for this uniqueNum, generate and store a new random fortune
        String newRandomFortune = getRandomFortune();
        getServletContext().setAttribute(storedKey, newRandomFortune);

        return newRandomFortune;
    }

    // Add your getUniqueNum method here
}


