<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:ns="http://giangth.project.prx301/Schema/Products">
    <xsl:output method="html" />
    <xsl:template match="/">
        <xsl:apply-templates select="/ns:product" />
    </xsl:template>

    <xsl:template match="ns:product">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-sm-12 col-xs-12">
                    <div style="width: 585px; height: 450px">
                        <img src="{ns:avatarLink}" style="max-width: 100%; max-height: 100%" />
                    </div>
                </div>
                <div class="col-md-6 col-sm-12 col-xs-12">
                    <h2>
                        <xsl:value-of select="ns:name" />
                    </h2>
                    <h2>
                        <xsl:value-of select="ns:author" />
                    </h2>
                    <h3>
                        <strong>
                            <xsl:value-of select="ns:category" />
                        </strong>
                    </h3>
                    <h3>
                        <strong>Mã:</strong>
                        <xsl:value-of select="ns:code" />
                    </h3>
                    <h2>
                        <strong>Giá:</strong>
                        <xsl:value-of select="format-number(ns:price, '###,###,###')" /> VNĐ
                    </h2>
                    <br />
                    <br />
                    <a class="btn btn-success" href="{ns:productLink}">
                        Xem trên <xsl:value-of select="ns:siteKey" />
                    </a>
                </div>
            </div>
        </div>
    </xsl:template>
</xsl:stylesheet>