package com.jxlt.udic.riskcontrol.swagger2.controller;

import com.jxlt.udic.riskcontrol.website.enums.ResponseResultCodeEnum;
import com.jxlt.udic.riskcontrol.swagger2.dto.Request;
import com.jxlt.udic.riskcontrol.swagger2.dto.Table;
import com.jxlt.udic.riskcontrol.swagger2.service.WordService;
import com.jxlt.udic.riskcontrol.website.model.RootResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiuYin.Cui on 2018/1/11.
 */
@Api(description = "获取swagger的word数据")
@Slf4j
@RestController
public class WordController {

    @Autowired
    private WordService tableService;

    @ApiOperation(value = "获取word格式数据", notes = "调用swagger-json接口获取swagger-json数据并转换为word格式数据")
    @RequestMapping(value = "/getWord", method = RequestMethod.GET, produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public RootResult<List<Table>> getJson(HttpServletResponse response, @ApiParam(value = "组名", required = true) @RequestParam String groupName) {
        try {
            List<Table> list = tableService.tableList(groupName);
            String docHtmlBody = getDocHtmlBody(list);
            response.setContentType("application/doc; charset=GB2312");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + groupName + "Api.doc\"");
            response.setCharacterEncoding("GB2312");
//            response.getOutputStream().write(createWord(list));
            response.getOutputStream().write(docHtmlBody.getBytes("GB2312"));
            response.flushBuffer();
        } catch (Throwable e) {
            log.error(ExceptionUtils.getFullStackTrace(e));
            RootResult<List<Table>> result = new RootResult<>();
            result.setCode(-1);
            result.setErrorInfo(ResponseResultCodeEnum.REQUEST_PARAMS_ERROR);
            return result;
        }

        return null;
    }

    private String getDocHtmlBody(List<Table> list) {
        String htmlHead =
                "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "    <head>\n" +
                        "        <title>tool</title>\n" +
                        "        <style type=\"text/css\">\n" +
                        "            .bg {\n" +
                        "            background-color: rgb(84, 127, 177);\n" +
                        "            }\n" +
                        "\n" +
                        "            tr {\n" +
                        "            height: 20px;\n" +
                        "            font-size: 12px;\n" +
                        "            }\n" +
                        "\n" +
                        "            .specialHeight {\n" +
                        "            height: 40px;\n" +
                        "            }\n" +
                        "        </style>\n" +
                        "    </head>\n" +
                        "    <body>\n";

        String htmlHeadBody =
                "        <div style=\"width:800px; margin: 0 auto\">" +
                "           <h4><!-- title -->%s</h4> <!--这个是类的说明-->\n";
        String htmlBody =
                "                <h5><!-- tag -->%s</h5>   <!--这个是每个请求的说明，方便生成文档后进行整理-->\n" +
                "                <table border=\"1\" cellspacing=\"0\" cellpadding=\"0\" width=\"%s\">\n" +
                "                    <tr class=\"bg\">\n" +
                "                        <td colspan=\"4\"><c:out value=\"<!-- description -->%s\"/></td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td colspan=\"1\">接口描述</td>\n" +
                "                        <td colspan=\"3\"><!-- description -->%s</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td colspan=\"1\">URL</td>\n" +
                "                        <td colspan=\"3\"><!-- url -->%s</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td colspan=\"1\">请求方式</td>\n" +
                "                        <td colspan=\"3\"><!-- requestType -->%s</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td colspan=\"1\">请求类型</td>\n" +
                "                        <td colspan=\"3\"><!-- requestForm -->%s</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td colspan=\"1\">返回类型</td>\n" +
                "                        <td colspan=\"3\"><!-- responseForm -->%s</td>\n" +
                "                    </tr>\n" +
                "\n" +
                "                    <tr class=\"bg\" align=\"center\">\n" +
                "                        <td>参数名</td>\n" +
                "                        <td>数据类型</td>\n" +
//                "                        <td>参数类型</td>\n" +
                "                        <td>是否必填</td>\n" +
                "                        <td>说明</td>\n" +
                "                    </tr>\n" +
                "                    <!-- requestList -->%s\n" +
//                "                    <tr class=\"bg\" align=\"center\">\n" +
//                "                        <td>状态码</td>\n" +
//                "                        <td colspan=\"4\">描述</td>\n" +
////                "                        <td colspan=\"3\">说明</td>\n" +
//                "                    </tr>\n" +
                "\n" +
//                "                    <!-- responseList -->%s\n" +
                "\n" +
//                "                    <tr class=\"bg\">\n" +
//                "                        <td colspan=\"4\">示例</td>\n" +
//                "                    </tr>\n" +
                "                    <tr class=\"specialHeight\">\n" +
                "                        <td colspan=\"4\" class=\"bg\">请求参数</td>\n" +
                "                    </tr>\n" +
                "                    <tr class=\"specialHeight\">\n" +
                "                        <td colspan=\"4\"><!-- requestParam -->%s</td>\n" +
                "                    </tr>\n" +
                "                    <tr class=\"specialHeight\">\n" +
                "                        <td colspan=\"4\" class=\"bg\">返回值</td>\n" +
                "                    </tr>\n" +
                "                    <tr class=\"specialHeight\">\n" +
                "                        <td colspan=\"4\"><!-- responseParam -->%s</td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "            </div><br>" +
                "                ";
        String htmlRequestListBody = "" +
                "                        <tr>\n" +
                "                            <td><!-- name -->%s</td>\n" +
                "                            <td align=\"center\"><!-- type -->%s</td>\n" +
//                "                            <td><!-- paramType -->%s</td>\n" +
                "                            <td align=\"center\"><!-- require -->%s</td>\n" +
                "                            <td><!-- remark -->%s</td>\n" +
                "                        </tr>";
//        String htmlResponseListBody = "<tr align=\"center\">\n" +
//                "                            <td><!-- name -->%s</td></td>\n" +
//                "                            <td colspan=\"4\"><!-- description -->%s</td></td>\n" +
////                "                            <td colspan=\"3\"><!-- remark -->%s</td></td>\n" +
//                "                        </tr>";
        String htmlTail = "" +
                "    </body>\n" +
                "</html>\n";

        StringBuilder sb = new StringBuilder();
        sb.append(htmlHead);
        List<String> headList = new ArrayList<>();
        for (Table headTable : list) {
            if (headList.contains(headTable.getTitle())) {
                continue;
            }
            headList.add(headTable.getTitle());
            sb.append(String.format(
                    htmlHeadBody,
                    headTable.getTitle()
            ));
            for (Table table : list) {
                if (!table.getTitle().equals(headTable.getTitle())) {
                    continue;
                }
                StringBuilder sbRequestList = new StringBuilder();
                for (Request request : table.getRequestList()) {
                    sbRequestList.append(String.format(
                            htmlRequestListBody,
                            request.getName(),
                            request.getType(),
//                        request.getParamType(),
                            request.getRequire() ? "是" : "否",
                            request.getRemark()
                    ));
                }
//
//            StringBuilder sbResponseList = new StringBuilder();
//            for (Response response : table.getResponseList()) {
//                sbResponseList.append(String.format(
//                        htmlResponseListBody,
//                        response.getName(),
//                        response.getDescription()
////                        response.getRemark()
//                        )
//                );
//            }

                sb.append(String.format(
                        htmlBody,
                        table.getTag(),
                        "100%",
                        table.getDescription(),
                        table.getDescription(),
                        table.getUrl(),
                        table.getRequestType(),
                        table.getRequestForm(),
                        table.getResponseForm(),
                        sbRequestList.toString(),
//                    sbResponseList.toString(),
                        table.getRequestParam(),
//                    ""
                        table.getResponseParam()
                ));
            }
        }
        sb.append(htmlTail);
        return sb.toString();
    }

}
