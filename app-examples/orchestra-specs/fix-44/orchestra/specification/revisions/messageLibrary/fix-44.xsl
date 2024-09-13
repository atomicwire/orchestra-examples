<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fixr="http://fixprotocol.io/2020/orchestra/repository">

  <xsl:template match="@* | node()">
    <xsl:copy>
      <xsl:apply-templates select="@* | node()"/>
    </xsl:copy>
  </xsl:template>

  <!--
    The message XMLnonFIX (n) contains no non-StandardHeader/Trailer fields which causes QuickFIX to reject the data
    dictionary.
  -->
  <xsl:template match="fixr:message[@name='XMLnonFIX']"/>

</xsl:stylesheet>
