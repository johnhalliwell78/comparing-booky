<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:ns="http://giangth.project.prx301/Schema/Products">

    <xsl:output method="html" />

    <xsl:template match="/">
        <xsl:apply-templates select="/ns:products" />
    </xsl:template>

    <xsl:template match="ns:products">

        <div class="container">
            <div class="row">

                <xsl:for-each select="ns:product">
                    <div class="col-md-3 col-sm-6 col-xs-12">
                        <div class="product-item">
                            <div class="product-image">
                                <a href="/products/id={ns:id}" title="{ns:name}">
                                    <img src="{ns:avatarLink}" />
                                </a>
                            </div>
                            <div class="product-content">
                                <div class="price">
                                    <span>
                                        <xsl:value-of select="format-number(number(ns:price), '###,###,###')" /> VNĐ
                                    </span>
                                </div>
                                <a href="/products/id={ns:id}" title="{ns:name}">
                                    <h4>
                                        <strong>
                                            <xsl:value-of select="ns:name" />
                                        </strong>
                                    </h4>
                                </a>
                                <h5>
                                    <xsl:value-of select="ns:author" />
                                </h5>
                            </div>
                        </div>
                    </div>

                </xsl:for-each>
            </div>
        </div>

    </xsl:template>

</xsl:stylesheet>