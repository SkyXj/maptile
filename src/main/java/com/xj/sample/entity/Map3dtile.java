/**   
 * Copyright © 2018武汉中地数码科技有限公司. All rights reserved.
 * 
 * @Title: Map3dtile.java 
 * @Package: com.xj.sample.entity
 * @Description: TODO
 * @author: sky-1012262558@qq.com
 * @date: 2019-10-18 09:34:34
 * @Modify Description : 
 * @Modify Person : 
 * @version: V1.0   
 */

package com.xj.sample.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

/**
* 描述：模型
* @author sky
* @date 2019-10-18 09:34:34
*/
@Data
@TableName("map3dtile")
public class Map3dtile{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableField("Type")
    @Column(name = "Type",length = 10,nullable = false)
    private Integer type;


    /**
     *
     */
    @TableField("Zoom")
    @Column(name = "Zoom",length = 10,nullable = false)
    private Integer zoom;


    /**
     *
     */
    @TableField("X")
    @Column(name = "X",length = 10,nullable = false)
    private Integer x;


    /**
     *
     */
    @TableField("Y")
    @Column(name = "Y",length = 10,nullable = false)
    private Integer y;


    /**
     *
     */
    @TableField("Tile")
    @Column(name = "Tile",length = 10,nullable = false)
    private byte[] tile;
}