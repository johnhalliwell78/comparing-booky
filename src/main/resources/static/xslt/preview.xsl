<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:ns="http://giangth.project.prx301/Schema/Products">

    <xsl:output method="html" />

    <xsl:param name="compare" />

    <xsl:template match="/">
        <xsl:apply-templates select="ns:products" />
    </xsl:template>

    <xsl:template match="ns:products">

        <xsl:if test="count(ns:product) > 0">
            <div class="row">

                <xsl:for-each select="ns:product">
                    <div class="col-md-3 col-sm-6 col-xs-12">
                        <div class="product-item">
                            <div class="product-image">
                                <a href="/products/id={ns:id}" title="{ns:name}">
                                    <img src="{ns:avatarLink}" alt="{ns:name}" />
                                </a>
                            </div>
                        </div>
                        <div class="product-content">
                            <div class="price">
                                <xsl:if test="not($compare)">
                                    <span>
                                        <xsl:value-of select="format-number(number(ns:price), '###,###,###')" />
                                        VNĐ
                                    </span>
                                </xsl:if>
                                <xsl:if test="$compare">
                                    <xsl:if test="number(ns:price) &gt; $compare">
                                        <span style="color: red">
                                            <xsl:value-of select="format-number(number(ns:price), '###,###,###')" />
                                            VNĐ
                                            (giá cao hơn)
                                        </span>
                                    </xsl:if>
                                    <xsl:if test="number(ns:price) = $compare">
                                        <span>
                                            <xsl:value-of select="format-number(number(ns:price), '###,###,###')" />
                                            VNĐ
                                        </span>
                                    </xsl:if>
                                    <xsl:if test="number(ns:price) &lt; $compare">
                                        <span style="color: blue">
                                            <xsl:value-of select="format-number(number(ns:price), '###,###,###')" />
                                            VNĐ
                                            (giá thấp hơn)
                                        </span>
                                    </xsl:if>
                                </xsl:if>

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
                </xsl:for-each>
            </div>
        </xsl:if>
        <xsl:if test="count(ns:product) = 0">
            <div>
                <h4>(Không có)</h4>
            </div>
        </xsl:if>
    </xsl:template>
</xsl:stylesheet>