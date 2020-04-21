package com.alimama.server.chains;

import com.alimama.server.commands.Commands1;
import com.alimama.server.commands.Commands2;
import org.apache.commons.chain.impl.ChainBase;

/**
 * apache commons-chain 阿帕奇链式逻辑处理方案
 * Created by PengWX on 2020/4/21.
 */
public class CommandChain extends ChainBase {
    public CommandChain() {
        addCommand(new Commands1());
        addCommand(new Commands2());
    }
}