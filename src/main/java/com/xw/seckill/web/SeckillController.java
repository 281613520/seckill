package com.xw.seckill.web;

import com.xw.seckill.dto.Exposer;
import com.xw.seckill.dto.SeckillExecution;
import com.xw.seckill.dto.SeckillResult;
import com.xw.seckill.entity.SecKill;
import com.xw.seckill.enums.SeckillStateEnum;
import com.xw.seckill.exception.RepeatKillException;
import com.xw.seckill.exception.SeckillCloseException;
import com.xw.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by AnKh on 2017/2/12.
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    SeckillService seckillService;
    
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        List<SecKill> secKillList = seckillService.getSeckillList();
        model.addAttribute("list",secKillList);
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId")Long seckillId, Model model){
        if(seckillId == null) return "redirect:/seckill/list";

        SecKill secKill = seckillService.getById(seckillId);

        if(secKill ==  null) return "forward:/seckill/list";

        model.addAttribute("seckill",secKill);

        return "detail";
    }

    @RequestMapping(method = RequestMethod.POST,
            value = "/{seckillId}/exposer",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){
        SeckillResult<Exposer> result;
        try{
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true,exposer);
        }catch(Exception e){
            e.printStackTrace();
            return new SeckillResult<Exposer>(false,e.getMessage());
        }

        return result;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/excute",
    method = RequestMethod.POST,
    produces = "application/json;charset=utf-8")
    public SeckillResult<SeckillExecution> excute(@PathVariable("seckillId") Long seckillId,
                                                  @PathVariable("md5") String md5,
                                                  @CookieValue(value = "killPhone",required = false)Long phone){
        if(phone == null) return new SeckillResult<SeckillExecution>(false,"未注册");

        SeckillResult<SeckillExecution> result;//这个result是干嘛的？

        try{
            SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId,phone,md5);
            return new SeckillResult<SeckillExecution>(true,seckillExecution);
        }catch(RepeatKillException e1){
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExecution>(false,seckillExecution);
        }catch(SeckillCloseException e2){
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.END);
            return new SeckillResult<SeckillExecution>(false,seckillExecution);
        }catch (Exception e3){
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(false,seckillExecution);
        }
    }

    @RequestMapping(value = "/time/now",
            method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> date(){
        Date date = new Date();
        return new SeckillResult<Long>(true,date.getTime());
    }
}
