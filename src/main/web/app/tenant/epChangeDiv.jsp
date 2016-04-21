<%--
  Created by IntelliJ IDEA.
  User: mk
  Date: 16/4/21
  Time: 下午7:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<div class="panel panel-blue">
    <div class="panel-heading dark-overlay"><s:property value="name" /></div>
    <div class="timeline-body">
        <ul class="timeline">
            <div class="pre-scrollable">
                <s:iterator value="list" status="st">
                <li>
                    <div class="tl-circ"></div>
                    <div class="timeline-panel">
                        <div class="tl-heading">
                            <h4><s:property value="pName" /></h4>
                            <h5><s:property value="dName" /></h5>
                            <small class="text-muted"><i class="glyphicon glyphicon-time"></i><s:property value="date" /></small>
                        </div>
                    </div>
                </li>
                </s:iterator>
            </div>
        </ul>
    </div>
</div>

<script>
    $(".timeline>div>li:odd").addClass("timeline-inverted");
</script>