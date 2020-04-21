package com.hadi.chat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import zendesk.chat.Chat
import zendesk.chat.ChatEngine
import zendesk.core.AnonymousIdentity
import zendesk.core.Identity
import zendesk.core.Zendesk
import zendesk.messaging.MessagingActivity
import zendesk.support.Support
import zendesk.support.guide.HelpCenterActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Zendesk.INSTANCE.init(
            this, "https://d3v-coffeechat.zendesk.com",//add your domain here
            "4624842f0ad9b3e98615654b6334a6854aafaf743aa931e7", //add your application id here
            "mobile_sdk_client_6f4fe5ffe8fb47d30ab6" //add Oauth client id here
        )

        Chat.INSTANCE.init(this, "i1EOVaI6Fbj01H1T7IqOYgIo42GcYvCz") // add your account key here

        Support.INSTANCE.init(Zendesk.INSTANCE)

        val identity: Identity = AnonymousIdentity()
        Zendesk.INSTANCE.setIdentity(identity)

        help_button.setOnClickListener {
            HelpCenterActivity.builder().show(this)
        }

        chat_button.setOnClickListener {
            MessagingActivity.builder()
                .withEngines(ChatEngine.engine())
                .show(it.context)
        }
    }
}
